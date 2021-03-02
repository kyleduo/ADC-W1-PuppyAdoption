package com.example.androiddevchallenge.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.repository.PetsRepo
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

/**
 * @author zhangduo on 2021/3/1
 */
class ListFragment : Fragment() {

    @Suppress("UNCHECKED_CAST")
    private val viewModel: ListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListViewModel(PetsRepo(requireContext())) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    PetListing(viewModel.pets)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPets()
    }

    // Start building your app here!
    @Composable
    fun PetListing(pets: LiveData<List<Pet>>) {
        Surface(color = MaterialTheme.colors.background) {
            val petsList = pets.observeAsState()
            petsList.value?.let {
                LazyColumn {
                    this.items(it) { pet ->
                        PetItem(pet = pet)
                    }
                }
            }
        }
    }

    @Composable
    fun PetItem(pet: Pet) {
        Row {
            GlideImage(data = pet.photo) {
            }
//            Text(text = pet.name)
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun PreviewLight() {
        MyTheme {
        }
    }

}


