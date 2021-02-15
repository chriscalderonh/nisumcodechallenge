package com.chriscalderonh.nisumcodechallenge.common.presentation.execution

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class FakeExecutionThread: ExecutionThread {

    override fun schedulerForObserving(): Scheduler = Schedulers.trampoline()

    override fun schedulerForSubscribing(): Scheduler = Schedulers.trampoline()

}