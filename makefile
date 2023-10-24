# Makefile for the A* Path Finder

JFLAGS = -g
JC = javac
BINDIR = bin
SRCDIR = src

# Explicitly list down the Java source files in the required order
SOURCES = $(SRCDIR)/PathFinder.java $(SRCDIR)/Main.java
CLASSES = $(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

all: $(CLASSES)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JC) $(JFLAGS) -d $(BINDIR) -cp $(BINDIR) $<

clean:
	$(RM) $(BINDIR)/*.class

run: all
	java -cp $(BINDIR) Main

# Declare the targets that don't represent filenames
.PHONY: default run clean