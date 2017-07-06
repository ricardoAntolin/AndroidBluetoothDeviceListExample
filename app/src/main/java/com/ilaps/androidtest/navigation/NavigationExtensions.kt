package com.ilaps.androidtest.navigation

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import com.ilaps.androidtest.common.BaseFragment


fun Activity.navigateToActivityRemovingPrevious(classToStartIntent: Class<*>) {
    this.navigateToActivityRemovingPrevious(classToStartIntent, null)
}

fun Activity.navigateToActivityRemovingPrevious(classToStartIntent: Class<*>, extras: Bundle?) {
    val intent = Intent(this.applicationContext, classToStartIntent)
    if (extras != null) {
        intent.putExtras(extras)
    }
    this.startActivity(intent)
    this.finish()
}

fun Activity.navigateToActivityRemovingPreviousWithExtrasClearTop(
        classToStartIntent: Class<*>, extras: Bundle) {
    this.navigateToActivityWithExtrasClearTop(classToStartIntent, extras, false, 0, 0)
    this.finish()
}

fun Activity.navigateToActivityRemovingPreviousWithExtrasClearTopAndAnimation(
        classToStartIntent: Class<*>, extras: Bundle, inTransition: Int, outTransition: Int) {
    this.navigateToActivityWithExtrasClearTop(classToStartIntent, extras, true, inTransition, outTransition)
    this.finish()
}

fun Activity.navigateToActivity(classToStartIntent: Class<*>) {
    this.navigateToActivity(classToStartIntent, null, false, 0, 0)
}

fun Activity.navigateToActivityWithAnimation(classToStartIntent: Class<*>,
                                             inTransition: Int, outTransition: Int) {
    this.navigateToActivity(classToStartIntent, null, true, inTransition, outTransition)
}

fun Activity.navigateToActivity(classToStartIntent: Class<*>,
                                extras: Bundle?) {
    this.navigateToActivity(classToStartIntent,extras,false,0,0)
}

fun Activity.navigateToActivity(classToStartIntent: Class<*>,
                                extras: Bundle?, haveTransition: Boolean,
                                inTransition: Int, outTransition: Int) {
    val intent = Intent(this.applicationContext, classToStartIntent)

    if (extras != null) {
        intent.putExtras(extras)
    }
    this.startActivity(intent)
    if (haveTransition) {
        this.setActivitityTransitionAnimation(inTransition, outTransition)
    }
}

fun Activity.navigateToActivityWithExtrasClearTop(classToStartIntent: Class<*>, extras: Bundle?,
                                                  haveTransition: Boolean,
                                                  inTransition: Int, outTransition: Int) {
    val intent = Intent(this.applicationContext, classToStartIntent)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

    if (extras != null) {
        intent.putExtras(extras)
    }
    this.startActivity(intent)
    if (haveTransition) {
        this.setActivitityTransitionAnimation(inTransition, outTransition)
    }

}

fun Activity.navigateToActivityRemovingPreviousClearTop(classToStartIntent: Class<*>,
                                                        haveTransition: Boolean, inTransition: Int, outTransition: Int) {
    val intent = Intent(this.applicationContext, classToStartIntent)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    this.startActivity(intent)
    if (haveTransition) {
        this.setActivitityTransitionAnimation(inTransition, outTransition)
    }
    this.finish()
}

fun Activity.setActivitityTransitionAnimation(inTransition: Int, outTransition: Int) {
    this.overridePendingTransition(inTransition, outTransition)
}

fun Activity.isFragmentAlreadyInForeground(fragmentTag: String): Boolean {
    val foundFragment = this.fragmentManager
            .findFragmentByTag(fragmentTag)

    return foundFragment != null && foundFragment.isVisible
}

fun Activity.getBackStackSize(): Int {
    return this.fragmentManager.backStackEntryCount
}

fun Activity.navigateToActivityForResult(classToStartIntent: Class<*>, extras: Bundle?, requestCode: Int) {
    this.navigateToActivityForResult(classToStartIntent,extras,requestCode,false,0,0)
}

fun Activity.navigateToActivityForResult(classToStartIntent: Class<*>, extras: Bundle?,
                                         requestCode: Int, haveTransition: Boolean,
                                         inTransition: Int, outTransition: Int) {
    val intent = Intent(this.applicationContext, classToStartIntent)
    if (extras != null) {
        intent.putExtras(extras)
    }
    this.startActivityForResult(intent, requestCode)
    if (haveTransition) {
        this.setActivitityTransitionAnimation(inTransition,outTransition)
    }
}

fun Activity.navigateToFragment(fragment: Fragment, contentFrame: Int, addToBackStack: Boolean) {
    this.pushFragment(fragment, contentFrame, addToBackStack, 0, 0, 0, 0)
}

fun Activity.navigateToFragment(fragment: Fragment, contentFrame: Int, addToBackStack: Boolean,
                                anim: Int, anim2: Int, animOut: Int, animOut2: Int) {
    this.pushFragment(fragment, contentFrame, addToBackStack, anim, anim2, animOut, animOut2)
}

fun Activity.navigateToFragment(fragment: Fragment, contentFrame: Int, addToBackStack: Boolean, anim: Int, anim2: Int) {
    this.pushFragment(fragment, contentFrame, addToBackStack, anim, anim2, 0, 0)
}

fun Activity.navigateToFragmentCleanStack(fragment: Fragment, contentFrame: Int, addToBackStack: Boolean) {
    this.cleanBackStack(fragment)
    this.pushFragment(fragment, contentFrame, addToBackStack, 0, 0, 0, 0)
}

fun Activity.navigateToFragmentCleanStack(fragment: Fragment, contentFrame: Int, addToBackStack: Boolean,
                                          anim: Int, anim2: Int, animOut: Int, animOut2: Int) {
    this.cleanBackStack(fragment)
    this.pushFragment(fragment, contentFrame, addToBackStack, anim, anim2, animOut, animOut2)
}

fun Activity.navigateToFragmentCleanStack(fragment: Fragment, contentFrame: Int,addToBackStack: Boolean,
                                          anim: Int, anim2: Int) {
    this.cleanBackStack(fragment)
    this.pushFragment(fragment, contentFrame, addToBackStack, anim, anim2, 0, 0)
}

fun Activity.cleanBackStack(viewStringId: String) {
    this.fragmentManager.popBackStackImmediate(viewStringId,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
}


fun Activity.cleanBackStack(fragment: Fragment) {
    this.fragmentManager.popBackStackImmediate(null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun Activity.cleanBackStack() {
    this.fragmentManager.popBackStackImmediate(null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun Activity.removeCurrentFragment(fragment: Fragment) {
    val fragmentManager = this.fragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.remove(fragment)
    transaction.commit()
}

fun Activity.pushFragment(fragment: Fragment ,contentFrame: Int, addToBackStack: Boolean,
                          anim: Int, anim2: Int, animOut: Int, animOut2: Int) {
    val fragmentTransaction = this.fragmentManager
            .beginTransaction()
    if (anim > 0 && anim2 > 0) {
        if (animOut > 0 && animOut2 > 0) {
            fragmentTransaction.setCustomAnimations(anim, anim2, animOut, animOut2)
        } else {
            fragmentTransaction.setCustomAnimations(anim, anim2)
        }
    }


    val tag: String?
    if (fragment is BaseFragment) {
        tag = fragment.fragmentId
    } else {
        tag = (fragment as Any).javaClass.name
    }

    fragmentTransaction.replace(contentFrame, fragment, tag)

    if (addToBackStack) {
        fragmentTransaction.addToBackStack(tag)
    }

    fragmentTransaction.commit()
    if (contentFrame <= 0) {
        this.fragmentManager.executePendingTransactions()
    }


}