package org.romeo.instamarketApp.activities.content

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.romeo.instamarketApp.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity(), ContentActivityTemplate {
    private var presenter: ContentPresenter? = null
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ContentPresenter(this)
    }

    override fun initViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}