/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2020 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.zap.sharedutils;

import org.zaproxy.zap.sharedutils.binlist.BinList;
import org.zaproxy.zap.sharedutils.binlist.BinRecord;

/** A utility class for dealing with PII. */
public final class PiiUtils {

    private static BinList binList;

    private PiiUtils() {}

    /**
     * Checks whether a particular {@code String} input will pass a Credit Card Luhn validation
     * check.
     *
     * @param inStr the value to be checked.
     * @return {@code true} if the value passes the Luhn check, {@code false} otherwise.
     */
    public static boolean isValidLuhn(String inStr) {
        int sum = 0;
        int parity = inStr.length() % 2;
        for (int index = 0; index < inStr.length(); index++) {
            int digit = Integer.parseInt(inStr.substring(index, index + 1));
            if ((index % 2) == parity) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return (sum % 10) == 0;
    }

    private static BinList getBinList() {
        if (binList == null) {
            binList = new BinList();
        }
        return binList;
    }

    public static BinRecord getBinRecord(String candidate) {
        return getBinList().get(candidate);
    }
}