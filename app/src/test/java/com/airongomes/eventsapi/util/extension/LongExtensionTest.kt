package com.airongomes.eventsapi.util.extension

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.airongomes.eventsapi.BaseAndroidJUnitTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LongExtensionTest: BaseAndroidJUnitTest() {

    private val timestamp = 1534784400000L

    @Test
    fun `Given timestamp When calling toDate() Then return string with date`() {
        val date = timestamp.toDate()
        assertThat(date, `is`("20 Aug 2018"))
    }

    @Test
    fun `Given timestamp When calling toDateTime() Then return string with date and time`() {
        val date = timestamp.toDateTime()
        assertThat(date, `is`("20 Aug 2018 às 2:00 PM"))
    }

    @Test
    fun `Given timestamp When calling getCalendar() Then return calendar with correct timestamp`() {
        val calendar = timestamp.getCalendar()
        assertThat(calendar.timeInMillis, `is`(1534784400000L))
    }
}