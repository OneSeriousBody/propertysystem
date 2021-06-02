package com.coderwzt.config;

/**
 * @Author: coderwzt
 * @Date: 2020/11/27 11:22
 * @Version 1.0
 */
public class AlipayConfig {


    // 作为身份标识的应用ID
    public static String app_id = "2016102100733279";

    //你的私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCHEaMsiJI+XhW4eUbHiw9ZSwM/1Twsmfx3jGFy0hbbkIibNQ1y0fn/ha5Bzk3wABGZSAWLjzXrERC2e7aGp4A9tMkgeu0DvYt4Qz7+H17JMbDF7oHZSM832n089Re1kDOvTx1y8D/eg7ZgbwHs5O+YjGQyxk6UaSVDbP4o2b203xfRHZBl+qsltfS+GFsrhdkmZCJHu5wpgzZ7zHMw3ykEnWRIIQpwi4H7ri32EzRNlgn21PnkTw991KWGphBChn+NL8vFyqIX5CXFA0NxiGcEC8ZFw9gwpEGfQVpf38fzvlrH6omt60IvbQNnKENRj6M0PB198LGNbaq6GZ3816xFAgMBAAECggEAJ6pITxW0TNh4bHpBDOyLj192DtLiHv8Bo2H4ZC3KRKSHWX0U+/kp/54UdILPZIHAqVD7P+bIoMWcMNrX2WdrQ7PJtN4kqIqLYL/uydYQkekjzpyiSVsWb4KccMBRPCKi6wLC1TQ0rXXkoyE2YYlBy3y/GCIghXjzVn+1i+2Vxn2liuGr+CBhSv22Q824RVOXDOfZX3ekFFvVb0IwX3mVADpFzfSVP5TfQ0HpmQhjEx/yyWzbB/iKF2ppQ7lL8Q+BN9e3NG49ymfb8TAtaLFBOTiZo4suuvk7ERZBbtHhYhuLEc7Opap8vU/YYZric5GD517npFaC5X4ztflOV9jQqQKBgQDpO1911SQn1GTXCUHAFBIqShHfwpvH64eXaATqe+i5zs9OY9/8KbZx+zBUl8ykPKxbwKq1ZrRcpC6L4hjLRBtPjI0BhsmhLLSQDUZpxyn5wb4v1SIz70NI8Vl4nUZoCtiSnrUlc5skQt6k/xJnFdeDQnAMhMjQacJh2zonRLlOqwKBgQCUQRo+uMp4NLtgx2F43cdRUXErMsZXxFr+4vEDTDNtuRLTuiLzq+v7piHl4rtZIzO3t5fWxOfmRjVtflDio1Zu+vUBeMr4WAy6+XjRkQ+GC+3VD7S9J1XFYngJRx4RPrugjs6J3Vjj9XynwhfGYuES3uX2zlmBDKtm0rp7QMMwzwKBgQCF0iZEssXrAIfXhh0uu5VspFE7JStC+1YyPmHLtMrkidASt2WWBs0C+1BQyUSG27U9c/++064bFa0LX/A/+NT+wLNLM+5OxxrXKHbFTJw+DLc2NMFKMTdXC/v9I6h5G/gFsIBZe8qBdMjQvPXvJe4hBybJWArs4jHA+guU6m60vwKBgDBZ4zNdlTsLB6mSnSmMOzs8MoqCLsnlEkBax3vp2EQKvicrZ8bM5VPXZOrSvYNexbk/OVgkM/WYJEinj726w6/CTTHDsX65SL2aK1s90jBHp7N7QdkHASQ07y0ncpjxOsQsjxPLivgd0h/sX1CmYv2CkMGjnvFFrokEZSlZf89lAoGBAOS7UZIxjQgRj1+od6FDIg8ulgZcJJPvLXvYvA9lmQ9GOOZyKh179AEE1tbzNEHgg8P64mS7ZB0+YoNGAQzp9w2nnkTjxPxqXmxcUwxU+zgdaUs13yUoCQLsxzM2XoKd75ItBFQiNTOkz1+c0gHIT0Q/iVaOAdSP+rEn5jCN2Eg9";

    //支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApqXb1OIn6SbFwp54YGtSy3aG1V7xo6+54pCVcTk9GO42viDFh6Uds9zeWK91SWVGx1ChIHwzpHfRGTe0tvNXQF+sGoVwV9Azyh/tu4WHxYQqSfsB9o14wWnjOE9kVE5WGzIboihSCSiA8HDMnlucumZGXIhzsGAWKX39Yhfike1SS/imCsAu5JzOmlzi4/LvWSp40uBYo6GBse1RAQKybHx61J/JWHNzw+yAX87/crfZ/MyqKoOX7inv/d0Jpq8VmoBpRjqba8tbZk2yzHYICPgx8zT2uMV49v+2N6kgi+ZciEiQ8gAlhrGtISBYiE3Hir++Lh4VI8BlL12HsIgcbQIDAQAB";

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
