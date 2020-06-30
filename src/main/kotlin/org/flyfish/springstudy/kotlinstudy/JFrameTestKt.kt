package org.flyfish.springstudy.kotlinstudy

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame

fun main(args: Array<String>) {
    val jFrame = JFrame("My Jframe")

    val jButton = JButton("My jbutton")

    jFrame.addWindowListener(object :WindowListener{
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
            println("windowClosing")
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
            println("windowActivated")
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
            println("windowOpened")
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

    })
    //对象表达式方式
//    jButton.addActionListener(object :ActionListener{
//        /**
//         * Invoked when an action occurs.
//         * @param e the event to be processed
//         */
//        override fun actionPerformed(e: ActionEvent?) {
//            println("actionPerformed")
//        }
//    })
    //lamda方式
    jButton.addActionListener(ActionListener { println("Button pressed") })
    //lamda 再省略 类型
    jButton.addActionListener { println("actionPerformed") }

    jFrame.add(jButton)
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

}