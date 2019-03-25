import os
import glob

dir_path = os.path.dirname(os.path.realpath(__file__))
cwd = os.getcwd()

for file in glob.glob("{}/expected/*.template".format(dir_path)):
    filename = os.path.basename(file)
    template = open(file).read()
    outputFile = open("{}/expected/{}.out".format(dir_path, filename.replace(".template", "")), 'w')
    outputFile.write(template.format(cwd))