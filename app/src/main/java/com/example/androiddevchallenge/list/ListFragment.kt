package com.example.androiddevchallenge.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.example.androiddevchallenge.ui.theme.MyTheme

/**
 * @author zhangduo on 2021/3/1
 */
class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    MyApp()
                }
            }
        }
    }

    // Start building your app here!
    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.background) {
            Text(text = "Ready... Set... GO!")
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun PreviewLight() {
        MyTheme {
            MyApp()
        }
    }

}


