package com.dzulfikar.subs3keloladata

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dzulfikar.subs3keloladata.ui.home.HomeActivity
import com.dzulfikar.subs3keloladata.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.detailMovieMainLayout)).check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.tvDetailMovieTitle)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieBrowserLabel)).check(matches(isDisplayed()))

        onView(withId(R.id.imgDetailMovie)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieReleaseDate)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieGenre)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieRating)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieRatingLabel)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieOverviewLabel)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailMovieOverviewText)).check(matches(isDisplayed()))

        onView(withId(R.id.detailNotFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.detailNotFavorite)).perform(click())

        onView(withId(R.id.detailIsFavorite)).check(matches(isDisplayed()))

        onView(withId(R.id.btnDetailMovieBrowser)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailMovieBrowserLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.btnDetailMovieBrowser)).perform(click())

    }

    @Test
    fun loadTvShows(){
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadDetailTvShows(){
        loadTvShows()

        onView(withId(R.id.rvTvShows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvShowMainLayout)).check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.tvDetailShowTitle)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowBrowserLabel)).check(matches(isDisplayed()))

        onView(withId(R.id.imgDetailTvShow)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowReleaseDate)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowGenre)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowRating)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowRating)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowOverviewTitle)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowOverviewText)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDetailShowSeason)).check(matches(isDisplayed()))

        onView(withId(R.id.detailNotFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.detailNotFavorite)).perform(click())

        onView(withId(R.id.detailIsFavorite)).check(matches(isDisplayed()))

        onView(withId(R.id.btnDetailShowBrowser)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailShowBrowserLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.btnDetailShowBrowser)).perform(click())

    }

    @Test
    fun loadFavoriteMovies(){
        onView(withId(R.id.menuFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menuFavorite)).perform(click())

        onView(withId(R.id.rvMovieFavorites)).check(matches(isDisplayed()))

        onView(withId(R.id.imageButtonFavoriteMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.imageButtonFavoriteMovie)).perform(click())
    }

    @Test
    fun loadFavoriteTvShows(){
        onView(withId(R.id.menuFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menuFavorite)).perform(click())

        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvShowFavorites)).check(matches(isDisplayed()))

        onView(withId(R.id.imageButtonFavoriteTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.imageButtonFavoriteTvShow)).perform(click())
    }


}