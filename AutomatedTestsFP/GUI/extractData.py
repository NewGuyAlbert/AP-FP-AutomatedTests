class ExtractData:

    def __init__(self, *args):
        self.testList = []
        self.websiteList = []
        self.extractData(args)

    def generator(self, fileName):
        elements = open(".\\data\\{}.txt".format(fileName))
        for element in elements:
            yield element.rstrip()

    def extractData(self, dataObj):
        if(dataObj[0]):
            self.testList = list(self.generator(dataObj[0]))
        if(dataObj[1]):
            self.websiteList = list(self.generator(dataObj[1]))