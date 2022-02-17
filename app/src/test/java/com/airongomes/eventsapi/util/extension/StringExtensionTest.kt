package com.airongomes.eventsapi.util.extension

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.airongomes.eventsapi.BaseAndroidJUnitTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.everyItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StringExtensionTest: BaseAndroidJUnitTest() {

    @Test
    fun `Given null value When calling toStringOrEmpty() Then return empty string`(){
        val value = null
        val result = value.toStringOrEmpty()
        assertThat(result, `is`(""))
    }

    @Test
    fun `Given empty string When calling hasValidField() Then return false`() {
        val value = ""
        val result = value.hasValidField()
        assertThat(result, `is`(false))
    }

    @Test
    fun `Given blank string When calling hasValidField() Then return false`() {
        val value = "     "
        val result = value.hasValidField()
        assertThat(result, `is`(false))
    }

    @Test
    fun `Given filled string When calling hasValidField() Then return true`() {
        val value = "This string is filled."
        val result = value.hasValidField()
        assertThat(result, `is`(true))
    }

    @Test
    fun `Given empty string When calling hasValidEmail() Then return false`() {
        val value = ""
        val result = value.hasValidEmail()
        assertThat(result, `is`(false))
    }

    @Test
    fun `Given blank string When calling hasValidEmail() Then return false`() {
        val value = "       "
        val result = value.hasValidEmail()
        assertThat(result, `is`(false))
    }

    @Test
    fun `Given invalid email format When calling hasValidEmail() Then return false`() {
        val value = listOf(
            "test.com",
            "test@.com",
            "test@gmail",
            "@gmail.com"
        )
        val result = mutableListOf<Boolean>()
        value.forEach {
            result.add(it.hasValidEmail())
        }
        assertThat(result, everyItem(`is`(false)))
    }

    @Test
    fun `Given valid email format When calling hasValidEmail() Then return true`() {
        val value = "test@gmail.com"
        val result = value.hasValidEmail()
        assertThat(result, `is`(true))
    }
}

