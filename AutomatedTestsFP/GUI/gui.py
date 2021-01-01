from extractData import ExtractData
from tkinter import *
from tkinter import ttk
import os
import subprocess
import time
import sys

class gui:
    def __init__(self,window):
        self.master = window
        self.frames = []
        self.testList = []
        self.website = StringVar()
        self.data = self.extractAllData()
        self.env = "Test"
    
    def __call__(self):
        self.createWindow()
    
    def createWindow(self):
        self.createFrames()

        self.createLabel("Automated Tests")

        self.createOptionMenu()

        for item in self.data.testList:
            self.createCheckbox(item)

        self.createHLine()

        self.createButton()

        self.master.mainloop()

    def createFrames(self):
        topFrame = Frame(self.master, borderwidth=2, pady=2)
        topFrame.pack()
        middleFrame = Frame(self.master, borderwidth=2, pady=5)
        middleFrame.pack(fill=BOTH)
        bottomFrame = Frame(self.master, borderwidth=2, pady=5)
        bottomFrame.pack()
        self.frames = [topFrame, middleFrame, bottomFrame]

    def createLabel(self, labelText):
        greeting = Label(self.frames[0], text=labelText, height='3', width='50', font=('Times','24'))
        greeting.pack()

    def createOptionMenu(self):
        self.website.set(self.data.websiteList[0]) #Default Value

        optionWindow = OptionMenu(self.frames[1], self.website, self.data.websiteList[0], *self.data.websiteList[1::]) #If I don't use slicing VSC python linter complains that i'm not passing a parameter even if it's not true
        optionWindow.config(font=("Times","14"), anchor="w")
        optionWindow.pack(fill="both")

    def createCheckbox(self, boxText):
        var1 = IntVar()
        Checkbutton(self.frames[1], text= boxText, variable= var1, font= ("Times", "12"), anchor='w').pack(fill='both')
        self.testList.append([var1,boxText])

    def createHLine(self):
        separator = ttk.Separator(self.frames[1], orient='horizontal')
        separator.pack(side='top', fill='x')

    def createButton(self):
        b = Button(self.frames[2], text="Run", command=self.runAllTests, font=("Times", "12"))
        b.pack(side = LEFT)

    def runAllTests(self):
        
        self.master.destroy()

        testsToRun = self.getTestList()

        command = 'cd C:\\Users\\danil\\OneDrive\\Desktop\\FinalProject\\automatedTests\\AutomatedTestsFP & mvn -Dtest={} -Dexec.domain="{}" -e test & py sendemail.py {} {} & PAUSE'.format(testsToRun, self.website.get(), self.website.get(), self.env)
        os.system(command)


    def getTestList(self):
        returnList = ''
        for item in self.testList:
            if(item[0].get() == 1):
                #returnList.append(item[1])
                returnList += item[1] + ',' 
        return returnList.rstrip(',')

    def extractAllData(self):
        return ExtractData("tests","websites")