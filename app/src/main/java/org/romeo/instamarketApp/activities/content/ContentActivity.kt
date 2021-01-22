package org.romeo.instamarketApp.activities.content

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.romeo.instamarketApp.databinding.ActivityContentBinding

/**
 * Activity that shows main user's content in 3 fragments.
 * Only one fragment has been already realized. @see UserFragment
 * */
class ContentActivity : AppCompatActivity(), ContentActivityTemplate {
    private var presenter: ContentPresenter? = null
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ContentPresenter(this)
    }

    override fun showProgressbar() {
        binding.mainProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        binding.mainProgressBar.visibility = View.INVISIBLE
    }

    override fun initViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}