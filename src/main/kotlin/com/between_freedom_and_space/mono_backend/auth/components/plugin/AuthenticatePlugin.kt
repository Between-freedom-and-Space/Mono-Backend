package com.between_freedom_and_space.mono_backend.auth.components.plugin

import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import mu.KotlinLogging

class AuthenticatePlugin(
    private val config: Configuration,
    private val processor: AuthenticateProcessor
) {
    companion object: Plugin<ApplicationCallPipeline, Configuration, AuthenticatePlugin> {

        override val key = AttributeKey<AuthenticatePlugin>("ApplicationAuthenticatePlugin")

        override fun install(
            pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit
        ): AuthenticatePlugin {
            val processor by inject<AuthenticateProcessor>()
            val config = Configuration().apply(configure)
            val plugin = AuthenticatePlugin(config, processor)

            pipeline.intercept(ApplicationCallPipeline.Call) {
                plugin.intercept(this)
            }

            return plugin
        }
    }

    data class Configuration(
        val enableLogging: Boolean = true
    )

    private val logger = KotlinLogging.logger { }

    private fun intercept(context: PipelineContext<Unit, ApplicationCall>) {
        val request = context.call.request
        try {
            processor.intercept(request)
        } catch (exception: Exception) {
            if (config.enableLogging) {
                logAuthenticateException(exception, request)
            }
            throw exception
        }
    }

    private fun logAuthenticateException(exception: Exception, request: ApplicationRequest) {
        logger.error(exception) {
            "Produced authenticate exception in request: $request in Authenticate Plugin"
        }
    }
}