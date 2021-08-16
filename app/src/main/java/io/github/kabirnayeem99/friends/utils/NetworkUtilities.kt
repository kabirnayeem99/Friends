package io.github.kabirnayeem99.friends.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


object NetworkUtilities {


    /**
     * Observers network status
     */
    fun observerNetwork(): Observable<Boolean> {
        val command = "ping -c 1 example.com"
        return Observable.just(Runtime.getRuntime().exec(command).waitFor() == 0)
            .observeOn(Schedulers.io())
            .onErrorReturn { false }
    }
}