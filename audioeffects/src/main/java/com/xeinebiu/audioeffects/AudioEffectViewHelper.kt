package com.xeinebiu.audioeffects

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.xeinebiu.audioeffects.dialogs.AudioEffectViewBottomSheetDialogFragment
import com.xeinebiu.audioeffects.dialogs.AudioEffectViewDialogFragment
import com.xeinebiu.audioeffects.views.AudioEffectView

class AudioEffectViewHelper constructor(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    private val audioEffectManager: AudioEffectManager,
) {
    fun asView(parent: ViewGroup? = null): View =
        createView(fragmentManager, parent).createView()
    fun showAsBottomSheet(): DialogFragment {
        val dialog = AudioEffectViewBottomSheetDialogFragment()
        dialog.onCreateViewListener = { _, viewGroup ->
            createView(dialog.childFragmentManager, viewGroup).createView()
        }
        dialog.show(fragmentManager, null)
        return dialog
    }

    fun showAsDialog(): DialogFragment {
        val dialog = AudioEffectViewDialogFragment()
        dialog.onCreateViewListener = { _, viewGroup ->
            createView(dialog.childFragmentManager, viewGroup).createView()
        }
        dialog.show(fragmentManager, null)
        return dialog
    }

    private fun createView(
        fragmentManager: FragmentManager,
        parent: ViewGroup?,
    ): AudioEffectView =
        AudioEffectView(fragmentManager, context, parent, audioEffectManager)
}
