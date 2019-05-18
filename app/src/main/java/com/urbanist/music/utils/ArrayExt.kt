package com.urbanist.music.utils

fun Array<*>.hasNull(): Boolean {
	forEach {
		if (it == null) {
			return true
		}
	}
	return false
}