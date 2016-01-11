# --------------------------------------------------------------------
# Makefile for University of Utah Sample Thesis
#
#	all	 make dvi file
#	pdf	 make pdf file
#	ps	 make postscript file
#	clean	 remove intermediate files
#	clobber	 remove everything
#	check	 doubled word and latex syntax check
#
# [28-May-2001] -- update for LaTeX2e
# [06-Jul-1993] 
# --------------------------------------------------------------------

LATEX = pdflatex
BIBTEX = bibtex
DETEX = detex -n
LACHECK = lacheck
DISTILL = ps2pdf -dMaxSubsetPct=100 -dCompatibilityLevel=1.3
DVIPS = dvips -Ppdf -tletter
FIG2EPS = fig2dev -Leps
DW = dw # ftp://ftp.math.utah.edu/pub/misc/dw.tar.gz
CP = cp
RM = rm -f
SED = sed
#SHELL = /bin/sh
SHELL = /bin/bash

#TEXFILES = \
	abstract.tex \
	appendix.tex \
	acknowledge.tex \
	chap1.tex \
	chap2.tex \
	chap3.tex \
	howto.tex

TEXFILES = howto.tex

FIGURES = 

FIGFILES =

EPSFILES = \
	$(FIGFILES:.fig=.eps)

# --------------------------------------------------------------------

%.eps: %.fig
	$(FIG2EPS) $< >$@

all: howto.dvi

pdf: howto.pdf

ps: howto.ps

check: dw syn

#howto.dvi: howto.tex aliascnt.sty $(FIGURES) $(TEXFILES) $(EPSFILES)
howto.dvi: howto.tex $(FIGURES) $(TEXFILES) $(EPSFILES)
	@echo '-------------------- latex #1 --------------------'
	$(LATEX) --shell-escape howto.tex
	# @echo '-------------------- bibtex --------------------'
	# $(LATEX) --shell-escape howto.tex
	# $(BIBTEX) howto
	@echo '-------------------- latex #2 --------------------'
	$(LATEX) --shell-escape howto.tex
	@echo '-------------------- latex #3 --------------------'
	$(LATEX) --shell-escape howto.tex

howto.pdf: howto.ps
	@echo '-------------------- pdf --------------------'
	$(DISTILL) $?

howto.ps: howto.dvi
	@echo '-------------------- ps --------------------'
	$(DVIPS) -o $@ $?

dw:
	@-$(RM) howto.dw
	@echo '-------------------- doubled words --------------------'
	@for f in $(TEXFILES) ;\
	do \
		echo ----- $$f ----- ; \
		echo ----- $$f ----- >> howto.dw ; \
		$(DETEX) $$f  | $(DW) >> howto.dw ; \
	done

syn:
	@echo '-------------------- syntax check --------------------'
	@$(LACHECK) howto.tex

clean:
	@$(RM) $(FIGFILES:.fig=.eps) *.blg *.dvi* *.dw *.ilg \
	*.log *.o *.tmp *.old *~ \#* core

clobber: clean
	@$(RM) *.aux *.bbl *.idx *.ind \
	*.toc *.lof *.lot *.pdf *.ps
