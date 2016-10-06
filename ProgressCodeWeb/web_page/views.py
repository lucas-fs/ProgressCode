from django.shortcuts import render
import csv

from web_service.models import *
from .forms import *

def forms(request):
	return render(request, 'web_page/forms.html', locals())

def index(request):
	username = passwd = ''
	if request.POST:
			username = request.POST.get('username')
			passwd = request.POST.get('passwd')
	return render(request, 'web_page/index.html', locals())

def csv_reader(request):
	tabela = []
	if request.POST and request.FILES:
		csvFile = request.FILES['csv_file']
		for line in csvFile:
			tabela.append(line.decode("utf-8")[:-1].split(","))
		for i in range(len(tabela)):
			Inscrito.objects.create(nome=tabela[i][0], data_nasc=tabela[i][1], escola=tabela[i][2])
	return render(request, 'web_page/csv_reader.html', locals())

def evento_form(request):
	equipes = Equipe.objects.all()
	inscritos = Inscrito.objects.all()

	if request.method == "POST":
		form = EventoForm(request.POST)

		if form.is_valid():
			evento = form.save()

			for equipe in request.POST.getlist('check_equipes'):
				evEq = EventoEquipe(evento_id=evento.id, equipe_id=equipe)
				evEq.save()

			for inscrito in request.POST.getlist('check_inscritos'):
				evIn = EventoInscrito(evento_id=evento.id, inscrito_id=inscrito)
				evIn.save()

	else:
		form = EventoForm()
	return render(request, 'web_page/evento_form.html', locals())

def equipe_form(request):
	membros = Tutor.objects.all()

	if request.method == "POST":
		form = EquipeForm(request.POST)

		if form.is_valid():
			equipe = form.save()

			for membro in request.POST.getlist('check_tutores'):
				eqTut = EquipeTutor(equipe_id=equipe.id, tutor_id=membro)
				eqTut.save()

	else:
		form = EquipeForm()
	return render(request, 'web_page/equipe_form.html', locals())

def tutor_form(request):
	if request.method == "POST":
		form = TutorForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = TutorForm()
	return render(request, 'web_page/tutor_form.html', locals())

def encontro_form(request):
	if request.method == "POST":
		form = EncontroForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = EncontroForm()
	return render(request, 'web_page/encontro_form.html', locals())

def inscrito_form(request):
	if request.method == "POST":
		form = InscritoForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = InscritoForm()
	return render(request, 'web_page/inscrito_form.html', locals())

def feedback_form(request):
	if request.method == "POST":
		form = FeedbackForm(request.POST, request.FILES)
		if form.is_valid():
			form.save()
	else:
		form = FeedbackForm()
	return render(request, 'web_page/feedback_form.html', locals())

def atividade_form(request):
	if request.method == "POST":
		form = AtividadeForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = AtividadeForm()
	return render(request, 'web_page/atividade_form.html', locals())
