package org.flyfish.springstudy.kotlinstudy

import java.awt.event.WindowEvent
import java.awt.event.WindowListener


/**
 * 对象声明与对象表达式区别
 * 1、对象表达式是立刻初始化或者是运行的
 * 2、对象声明是延迟初始化的，在首次访问的时候进行初始化
 * 3、伴生对象是在其所对应的类被加载时初始化的，对应于Java的静态初始化
 */
object ObjBean{
    fun method() {

    }
}

//对象声明可以实现接口
object ObjBeanImp : WindowListener {
    /**
     * Invoked when a window is changed from a minimized
     * to a normal state.
     * @param e the event to be processed
     */
    override fun windowDeiconified(e: WindowEvent?) {

    }

    /**
     * Invoked when the user attempts to close the window
     * from the window's system menu.
     * @param e the event to be processed
     */
    override fun windowClosing(e: WindowEvent?) {
    }

    /**
     * Invoked when a window has been closed as the result
     * of calling dispose on the window.
     * @param e the event to be processed
     */
    override fun windowClosed(e: WindowEvent?) {
    }

    /**
     * Invoked when the Window is set to be the active Window. Only a Frame or
     * a Dialog can be the active Window. The native windowing system may
     * denote the active Window or its children with special decorations, such
     * as a highlighted title bar. The active Window is always either the
     * focused Window, or the first Frame or Dialog that is an owner of the
     * focused Window.
     * @param e the event to be processed
     */
    override fun windowActivated(e: WindowEvent?) {
    }

    /**
     * Invoked when a Window is no longer the active Window. Only a Frame or a
     * Dialog can be the active Window. The native windowing system may denote
     * the active Window or its children with special decorations, such as a
     * highlighted title bar. The active Window is always either the focused
     * Window, or the first Frame or Dialog that is an owner of the focused
     * Window.
     * @param e the event to be processed
     */
    override fun windowDeactivated(e: WindowEvent?) {
    }

    /**
     * Invoked the first time a window is made visible.
     * @param e the event to be processed
     */
    override fun windowOpened(e: WindowEvent?) {
    }

    /**
     * Invoked when a window is changed from a normal to a
     * minimized state. For many platforms, a minimized window
     * is displayed as the icon specified in the window's
     * iconImage property.
     * @param e the event to be processed
     * @see java.awt.Frame.setIconImage
     */
    override fun windowIconified(e: WindowEvent?) {
    }
}

fun main(args: Array<String>) {
    val obj = ObjBean
    obj.method()

    val obj2 = object {
        fun method2() {

        }
    }
    obj2.method2()
}