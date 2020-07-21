package com.asynctaskcoffee.moviestemplate.utils

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

open class KtRxBus @Inject constructor(private val publisher: PublishSubject<Any>) {

    var behaviors = mutableMapOf<String, BehaviorSubject<Any>>()
    fun publish(event: Any) {
        publisher.onNext(event)
    }

    @Synchronized
    private fun getBehavior(className: String): BehaviorSubject<Any> {
        var behaviorInternal = behaviors[className]
        if (behaviorInternal == null) {
            behaviorInternal = BehaviorSubject.create<Any>()
            behaviors[className] = behaviorInternal
        }
        return behaviorInternal
    }

    fun publishSticky(event: Any) {
        getBehavior(event.javaClass.name).onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

    fun <T> listenSticky(eventType: Class<T>): Observable<T> {
        return getBehavior(eventType.name).ofType(eventType)
    }

    fun restart() {
        behaviors.clear()
    }
}