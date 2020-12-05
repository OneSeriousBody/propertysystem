package com.yanzhen.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Author: xhh
 * @Date: 2020/11/27 11:22
 * @Version 1.0
 */
public class AlipayConfig {


    // 作为身份标识的应用ID
    public static String app_id = "2016100100637662";

    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCVoQtMacdr/+fuHZKtgA1+nQNorvo4B1w8YADOzIpv4PPl+env48zFhEM/yfAvRrA63/1X0AwsXQFMkX6PqupUEkl6AnIWW+G5i3hio5Ci/e9EpICnR+scFvtxazte3N2O3C3iqeTiLZwfV19yj6e0sKheKA674YsdAB9uo66jV4JLdZXKU9+iPcut2Govkx0iQliDw8YnIazigyGd3C5AmyL7eeegaY+O3hm4MmtGGAy3EXM0PNT5GHCVNESzsxGxO+kii8r2gg3lMh5hCnQAuzmtD+3ODSDwbpcRMeNtMnoIiBwkyV7rwCuacjk1JIVZn9KTQMO847+rrR/2e/uFAgMBAAECggEAK4FClroeyJEEb2Bb2GRmCA1BA3v0tru1HrzPH1GrYrImWBSc5yn1zOwS3YYqdqR38vZTY+fS0kdMbGXVRZJtRXp3sKjrYQLgE6GwpAVotWtd2Th3S6wGeQlZnHWQMpnHNZVhJgk9ymF2hkpSLy4Dle7TVWUSO6BtYUY63WFsEoOaKUMj2lJXxn09/5VMWcXqQACwNlXjI9XG1IZGf3F/18n5Zng8a/E6l+AAtzD8MAQwsSVRagNUjb3C5zQQnl9yXFVhFj1OjnHIgOMCjKGNfP/DHjGJ3LLK/vCcBPJc1Lr+4f3XC5uE7WvxosJJ4a4tIxj+HGgFBSAxgnqP6GLxNQKBgQDM5qe0P0MScdXYbOX07i3yOXJZMroC2f64jbtx8IaOBoDRI8O+j7Aa0dznsDa47srHoCdp33oMgPJw0j5ZRLPLWKh+TOJvm/Wq+O/WO6CGKIiakC04eWdqqcJ24ue1SlDrtwFAz6Q+a0HtmiYZQfaOLs4KqmY8d9n+U3rUh5GpPwKBgQC68bRMtNTvTWJ5x1TUFzhEqj9a/sn7Rj7gXO77kvLWBBju8XGvSjrknzLDCzK4cL4EwlsAilC9riN91YqXa4DUgKRTegR84HmsJZ5bqIXHHjdt+33Fn7ZO3z5BqpHl7EjOuZwfoQtFb1og8hkJgDQswUYjCE1/ZRqlzr5+tu2GOwKBgGTJH7x9UZSFAdbO2d28OkVnFGPjm7NDXxOQQ58r3EOH110H5oSZUNogWDRubLxSWNQQZtKg5fu4gYgVIi3uqTFgjqBxWP/lCtXqVK5f7fmPfoHoYRXiQYgpsEmiGq8Fuv/2qFskx3kccPVaWVsbIxeU1ixQ3LDpkwSSM1zOwkspAoGAKVpIGOOzapACpY+LWzMbtky2oQlVYqkCRZ0pHCtAXkkfvtNX43+XEXWJxA1ZFeWSkvjvKmpuO9cmcLJo6t/T6q3eK70E+x6oq4TSx9G6bfZoKoMEKlAPAtltKxdxGJww6tGyEGkOVjiwhjMniKt8Xx4NxrgSdIjH4T+fceZDGnsCgYEAmyauRCPq7zcECOCU/XNme0CYSn/5n1q4MTQ9pLPf88dqxd9mzT/gk2R+F8PUOBLsHvhlscPcBIPl+nQ/sKHC4P41Nvis+kjNmf8hxVA3NIeRB9ugDPGvafGXYBKqZZylnJCYHcvmIkt3UbmdAFeXmIEKjguYqRlN+4TmJKmvEYI=";//�̳̲鿴��ȡ��ʽ���������ã�

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhkXbe2PCeJmDBWJefKKcA3wzeFOEwQWnDCJV0G0Ui9Pt+qy6SHkP++zPkU3SDYgxXg4eN6KhVaHrG2RJ13nOgdiBlJbngDmkqyksE/g6wUIBn1SHAAdhk4fI07hE+TbJPrY72Qq96yyoIbz3CI0qZlimrS1mQIZWX492Y0AotFBBn7/1Ht1w9XCB5rJkOSj5znm82eUdLwLVZ7dDESno9Vsf/HQ9wBO+PZdJG0VDxCnb3G83hxUriUi+6IVNtyNBLBHU2w8TOMkDc3+3vX1s3caZhAHwMndrTZUByIb+YQnEhlePPzCjho7XPhYXcWngILS7t3InF9XexSLMO4NhVQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8888/payment/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8888/payment/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}
