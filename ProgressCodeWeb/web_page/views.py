from django.shortcuts import render
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.contrib import auth
from django.conf import settings

from web_service.models import *
from .forms import *

import csv, os

def index(request):
	username = passwd = ''
	if request.POST:
			username = request.POST.get('username')
			passwd = request.POST.get('passwd')
			user = authenticate(username=username, password=passwd)
			if user is not None:
				login(request, user)
				return render(request, 'web_page/forms.html', locals())
	return render(request, 'web_page/index.html', locals())

@login_required
def logout(request):
	auth.logout(request)
	return render(request, 'web_page/logout.html', locals())

@login_required
def forms(request):
	return render(request, 'web_page/forms.html', locals())

@login_required
def csv_reader(request):
	tabela = []
	if request.POST and request.FILES:
		csvFile = request.FILES['csv_file']
		for line in csvFile:
			tabela.append(line.decode("utf-8")[:-1].split(","))
		for i in range(len(tabela)):
			Inscrito.objects.create(nome=tabela[i][0], data_nasc=tabela[i][1], escola=tabela[i][2])
	return render(request, 'web_page/csv_reader.html', locals())

@login_required
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

@login_required
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

@login_required
def tutor_form(request):
	if request.method == "POST":
		form = TutorForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = TutorForm()
	return render(request, 'web_page/tutor_form.html', locals())

@login_required
def encontro_form(request):
	if request.method == "POST":
		form = EncontroForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = EncontroForm()
	return render(request, 'web_page/encontro_form.html', locals())

@login_required
def inscrito_form(request):
	if request.method == "POST":
		form = InscritoForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = InscritoForm()
	return render(request, 'web_page/inscrito_form.html', locals())

@login_required
def feedback_form(request):
	if request.method == "POST":
		form = FeedbackForm(request.POST, request.FILES)
		if form.is_valid():
			form.save()
	else:
		form = FeedbackForm()
	return render(request, 'web_page/feedback_form.html', locals())

@login_required
def atividade_form(request):
	if request.method == "POST":
		form = AtividadeForm(request.POST)
		if form.is_valid():
			form.save()
	else:
		form = AtividadeForm()
	return render(request, 'web_page/atividade_form.html', locals())


## Escolha de inserção de inscrito
@login_required
def choiceInsc(request):
	return render(request, "web_page/escolhaInsc.html", locals())

## Funções responsáveis pelas views tables
@login_required
def inscritos_list(request):
	inscritos = Inscrito.objects.all()
	if request.method == "POST":
		for inscrito in request.POST.getlist("check_inscritos"):
			Inscrito.objects.filter(id=inscrito).delete()
	return render(request, "web_page/inscritos_list.html", locals())

@login_required
def tutores_list(request):
	tutores = Tutor.objects.all()
	if request.method == "POST":
		for tutor in request.POST.getlist("check_tutores"):
			Tutor.objects.filter(id=tutor).delete()

	return render(request, "web_page/tutor_list.html", locals())

@login_required
def feedbacks_list(request):
	fb_result = Feedback.objects.raw("select f.id, f.status, f.timestamp, f.dir_audio, a.descricao, i.nome as nome_i, t.nome as nome_t from feedback f inner join inscritos i on f.inscrito_id = i.id inner join tutores t on f.tutor_id = t.id inner join atividades a on f.atividade_id = a.id")

	if request.method == "POST":
		for feedback in request.POST.getlist("check_fb"):
			teste = Feedback.objects.filter(id=feedback).values("dir_audio")
			path = os.path.join(settings.BASE_DIR, 'media') + "/" + teste[0]['dir_audio']
			if os.path.exists(path):
				os.remove(path)
			Feedback.objects.filter(id=feedback).delete()

	return render(request, "web_page/feedback_list.html", locals())

@login_required
def eventos_list(request):
	eventos = Evento.objects.all()
	if request.method == "POST":
		for evento in request.POST.getlist("check_eventos"):
			Evento.objects.filter(id=evento).delete()
	return render(request, "web_page/evento_list.html", locals())

@login_required
def encontros_list(request):
	id_recebido = request.POST.get("evento_id")
	evento_atual = Evento.objects.filter(id=id_recebido)
	aux_nome = evento_atual[0].nome_evento
	encontros = Encontro.objects.filter(evento_id=id_recebido)
	if request.method == "POST":
		for encont in request.POST.getlist("check_encontros"):
			Encontro.objects.filter(id=encont).delete()
	return render(request, "web_page/encontro_list.html", locals())

@login_required
def atividades_list(request):
	id_recebido = request.POST.get("encontro_id")
	encontro_atual = Encontro.objects.filter(id=id_recebido)
	aux_data = encontro_atual[0].data_realizao
	atividades = Atividade.objects.filter(encontro_id=id_recebido)
	if request.method == "POST":
		for ativ in request.POST.getlist("check_atividades"):
			Atividade.objects.filter(id=ativ).delete()
	return render(request, "web_page/atividades_list.html", locals())	