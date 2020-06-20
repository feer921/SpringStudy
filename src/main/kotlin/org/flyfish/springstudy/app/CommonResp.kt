package org.flyfish.springstudy.app

object CommonResp {
    const val STATUS_OK = 200
    const val STATUS_INNTER_ERROR = 500
    fun resp(msg: String?, statusCode: Int, respData: Any?): ServerResponse? {
        val serverResponse = ServerResponse()
        serverResponse.data = respData
        serverResponse.msg = msg
        serverResponse.statusCode = statusCode
        return serverResponse
    }

    fun respSuccess(msg: String?, respData: Any?): ServerResponse? {
        return resp(msg, STATUS_OK, respData)
    }

    fun respSuccess(respData: Any?):ServerResponse?{
        return respSuccess("请求成功", respData)
    }

    fun respError(msg: String?, statusCode: Int, respData: Any?): ServerResponse? {
        return resp(msg, statusCode, respData)
    }

    fun respError(msg: String?,respData: Any?) :ServerResponse?{
        return respError(msg, 400, respData)
    }

    fun respError(msg: String?): ServerResponse? {
        return respError(msg, null)
    }
}