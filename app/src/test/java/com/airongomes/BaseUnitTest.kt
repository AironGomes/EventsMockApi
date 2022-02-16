package com.airongomes

import org.junit.After
import org.koin.core.context.stopKoin

abstract class BaseUnitTest {
    @After fun tearDown() = stopKoin()
}