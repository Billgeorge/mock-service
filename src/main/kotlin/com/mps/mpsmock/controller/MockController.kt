package com.mps.mpsmock.controller

import com.mps.mpsmock.constant.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class MockController {

    var httpCodeResponseCodePost: HttpStatus = HttpStatus.OK
    var httpCodeResponseCodePut: HttpStatus = HttpStatus.OK
    var httpCodeResponseCodeGet: HttpStatus = HttpStatus.OK
    var httpCodeResponseCodePatch: HttpStatus = HttpStatus.OK

    var responsePost = mapOf<String, Any>()
    var responseGet = mapOf<String, Any>()
    var responsePut = mapOf<String, Any>()
    var responsePatch = mapOf<String, Any>()

    @PostMapping("/configure")
    fun configureCall(@RequestBody request: ConfigureRequest): ResponseEntity<String> {
        setResponseAndHttpCode(request.operation, request.response, request.httpCode)
        return ResponseEntity.ok("Done")
    }

    @GetMapping
    fun getToSimulate(): ResponseEntity<*> {
        return if (HttpStatus.OK == httpCodeResponseCodeGet) {
            ResponseEntity.ok(responseGet)
        } else {
            ResponseEntity.status(httpCodeResponseCodeGet).body("Not successful")
        }
    }

    @GetMapping("/verify")
    fun healthCheck(): ResponseEntity<*> {
        return ResponseEntity.ok("working fine")
    }

    @PutMapping
    fun putToSimulate(): ResponseEntity<*> {
        return if (HttpStatus.OK == httpCodeResponseCodePut) {
            ResponseEntity.ok(responsePut)
        } else {
            ResponseEntity.status(httpCodeResponseCodePut).body("Not successful")
        }
    }

    @PostMapping("/simulate")
    fun postToSimulate(): ResponseEntity<*> {
        return if (HttpStatus.OK == httpCodeResponseCodePost) {
            ResponseEntity.ok(responsePost)
        } else {
            ResponseEntity.status(httpCodeResponseCodePost).body("Not successful")
        }
    }

    @PatchMapping
    fun patchToSimulate(): ResponseEntity<*> {
        return if (HttpStatus.OK == httpCodeResponseCodePatch) {
            ResponseEntity.ok(responsePatch)
        } else {
            ResponseEntity.status(httpCodeResponseCodePatch).body("Not successful")
        }
    }

    private fun setResponseAndHttpCode(operation: String, response: Map<String, Any>, httpCode: Int) {
        val httpCode = HttpStatus.valueOf(httpCode)
        when (operation) {
            Operation.POST.opName -> {
                httpCodeResponseCodePost = httpCode
                if (HttpStatus.OK == httpCodeResponseCodePost) {
                    responsePost = response
                }
            }
            Operation.GET.opName -> {
                httpCodeResponseCodeGet = httpCode
                if (HttpStatus.OK == httpCodeResponseCodeGet) {
                    responseGet = response
                }
            }
            Operation.PUT.opName -> {
                httpCodeResponseCodePut = httpCode
                if (HttpStatus.OK == httpCodeResponseCodePut) {
                    responsePut = response
                }
            }
            Operation.PATCH.opName -> {
                httpCodeResponseCodePatch = httpCode
                if (HttpStatus.OK == httpCodeResponseCodePatch) {
                    responsePatch = response
                }
            }
        }
    }
}

data class ConfigureRequest(
    val operation: String,
    val response: Map<String, Any>,
    val httpCode: Int
)