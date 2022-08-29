package utils

import io.qameta.allure.Step

object PrepareSteps {

    @Step("Prepare some data")
    fun prepareData(value: String = EnvConfig.value): String {

        return ""
    }

    @Step("Get authorization token")
    fun getJWTToken(value: String = EnvConfig.value): String {

        return "Bearer %TOKEN%"
    }
}