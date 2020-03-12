package id.randiny.simplyautomatic.module

import android.content.Context

abstract class Module(
    identifier: Int,
    param: Map<String, String>,
    context: Context
) {
    open fun init() {

    }

    open fun poll(): Boolean {
        return false
    }

    open fun action() {}

    open fun setupListener(action: Module) {}

    open fun destroyListener() {}
}