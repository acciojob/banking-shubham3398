package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(!checkValidation(this.tradeLicenseId)){
            HashMap<Character, Integer> map = new HashMap<>();
            int size = this.tradeLicenseId.length();
            String id = this.tradeLicenseId;

            for (int i = 0; i < size; i++) {
                char ch = id.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            char[] licenseId = new char[size];
            int idx = 0;

            for(char ch : map.keySet()){
                int freq = map.get(ch);

                int currFreq = 1;
                for (int i = idx; i < size; i += 2) {
                    if (currFreq <= freq) {
                        licenseId[i] = ch;
                    } else {
                        break;
                    }
                    currFreq++;
                }
                if(currFreq <= freq) throw new Exception("Valid License can not be generated");
                while(licenseId[idx] != '\u0000' && idx < size-1){
                    idx++;
                }
            }
            //update the tradeLicenseId
            this.tradeLicenseId = new String(licenseId);

            if(!checkValidation(this.tradeLicenseId)){
                throw new Exception("Valid License can not be generated");
            }
        }

    }

    private boolean checkValidation(String tradeLicenseId){
        int n = tradeLicenseId.length();
        for(int i = 1; i < n; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i-1)){
                return false;
            }
        }
        return true;
    }

}
