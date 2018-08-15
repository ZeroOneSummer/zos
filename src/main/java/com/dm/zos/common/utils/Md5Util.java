package com.dm.zos.common.utils;

import cn.hutool.crypto.digest.DigestUtil;

public class Md5Util {

    public static String encry(String str){
      return  DigestUtil.md5Hex(str);
    }
}
