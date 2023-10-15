package jp.speakbuddy.catfact.domain.usecase

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import jp.speakbuddy.catfact.repository.FactRepository
import jp.speakbuddy.catfact.repository.model.Fact
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class GetFactWithMultipleCatsTest {

    @MockK
    lateinit var repository: FactRepository

    @Test
    fun `getFactWithMultipleCats should return fact with no multiple cats`() {
        val fact = "After humans, mountain lions have the largest range of any mammal in the Western Hemisphere."
        val length = fact.length
        every { repository.fetch() } returns flow {
            emit(Fact(fact = fact, length = length))
        }
        val getFactWithMultipleCats: GetFactWithMultipleCats = GetFactWithMultipleCatsUseCase(repository)

        runTest {
            val factWithMultipleCats = getFactWithMultipleCats().first()
            assertEquals(fact, factWithMultipleCats.fact)
            assertEquals(length, factWithMultipleCats.length)
            assertEquals(false, factWithMultipleCats.hasMultipleCats)
        }
    }

    @Test
    fun `getFactWithMultipleCats should return fact with multiple cats`() {
        val fact = "Cats can predict earthquakes. We humans are not 100% sure how they do it. There are several different theories."
        val length = fact.length
        every { repository.fetch() } returns flow {
            emit(Fact(fact = fact, length = length))
        }
        val getFactWithMultipleCats: GetFactWithMultipleCats = GetFactWithMultipleCatsUseCase(repository)

        runTest {
            val factWithMultipleCats = getFactWithMultipleCats().first()
            assertEquals(fact, factWithMultipleCats.fact)
            assertEquals(length, factWithMultipleCats.length)
            assertEquals(true, factWithMultipleCats.hasMultipleCats)
        }
    }
}
