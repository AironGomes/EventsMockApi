package com.airongomes.eventsapi

import org.junit.After
import org.koin.core.context.stopKoin

abstract class BaseAndroidJUnitTest {
    @After fun tearDown() = stopKoin()
}