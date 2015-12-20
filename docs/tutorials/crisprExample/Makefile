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

PDFLATEX = pdflatex
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
	top.tex

TEXFILES = top.tex

FIGURES = 

FIGFILES =

EPSFILES = \
	$(FIGFILES:.fig=.eps)

# --------------------------------------------------------------------

%.eps: %.fig
	$(FIG2EPS) $< >$@

all: top.dvi

pdf: top.pdf

ps: top.ps

check: dw syn

#top.dvi: top.tex aliascnt.sty $(FIGURES) $(TEXFILES) $(EPSFILES)
top.dvi: top.tex $(FIGURES) $(TEXFILES) $(EPSFILES)
	@echo '-------------------- latex #1 --------------------'
	$(PDFLATEX) --shell-escape top.tex
	@echo '-------------------- latex #2 --------------------'
	$(PDFLATEX) --shell-escape top.tex
	$(BIBTEX) top
	@echo '-------------------- latex #3 --------------------'
	$(PDFLATEX) --shell-escape top.tex

top.pdf: top.ps
	@echo '-------------------- pdf --------------------'
	$(DISTILL) $?

top.ps: top.dvi
	@echo '-------------------- ps --------------------'
	$(DVIPS) -o $@ $?

dw:
	@-$(RM) top.dw
	@echo '-------------------- doubled words --------------------'
	@for f in $(TEXFILES) ;\
	do \
		echo ----- $$f ----- ; \
		echo ----- $$f ----- >> top.dw ; \
		$(DETEX) $$f  | $(DW) >> top.dw ; \
	done

syn:
	@echo '-------------------- syntax check --------------------'
	@$(LACHECK) top.tex

clean:
	@$(RM) $(FIGFILES:.fig=.eps) *.blg *.dvi* *.dw *.ilg \
	*.log *.o *.tmp *.old *~ \#* core

clobber: clean
	@$(RM) *.aux *.bbl *.idx *.ind \
	*.toc *.lof *.lot *.pdf *.ps
