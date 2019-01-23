// IService.aidl
package com.example.a61979.alipayservice;

// Declare any non-default types here with import statements

interface IService {
    int callSafepay(String username, String password, float money, long timestamp);

}
