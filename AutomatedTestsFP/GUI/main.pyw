from gui import gui
from tkinter import *

if __name__ == "__main__":
    window = Tk()
    window.geometry("500x650")


    guiApp = gui(window)
    guiApp()
