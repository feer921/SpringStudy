package org.flyfish.springstudy.app

class ServerResponse {


    /**
     * 响应消息
     */
    var msg: String? = ""

    /**
     * 响应数据
     */
    var data: Any? = null;

    /**
     * 状态码
     */
    var statusCode: Int = 0

    /**
     * 当前服务器时间戳
     */
    var serverTime: Long = System.currentTimeMillis();





}