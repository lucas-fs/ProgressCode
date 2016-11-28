from django.shortcuts import render, render_to_response, redirect
from django.contrib.auth import login as django_login, authenticate, logout as django_logout
from django.contrib.auth.decorators import login_required
from django.contrib import auth
from django.contrib import messages
from django.conf import settings

from django.template import RequestContext

from web_service.models import *
from .forms import *

import csv, os

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request.POST)
        print(form)
        if form.is_valid():
            user = authenticate(email=request.POST['email'], password=request.POST['password'])
            if user is not None:
                if user.is_active:
                    django_login(request, user)
                    return render(request, 'web_page/forms.html', locals())
    else:
        form = AuthenticationForm()
    context = {
        'form': form,
    }
    return render(request, 'web_page/login.html', context)

def register(request):
    if request.method == 'POST':
        form = RegistrationForm(data=request.POST)
        if form.is_valid():
            user = form.save()
            return redirect('/')
    else:
        form = RegistrationForm()

    context = {
        'form': form,
    }
    return render(request, 'web_page/register.html', context)

@login_required
def logout(request):
    django_logout(request)
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
			messages.success(request, "Evento adicionado com sucesso")

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
				messages.success(request, "Equipe adicionada com sucesso")
	else:
		form = EquipeForm()
	return render(request, 'web_page/equipe_form.html', locals())

@login_required
def tutor_form(request):
	if request.method == "POST":
		form = TutorForm(request.POST)
		if form.is_valid():
			form.save()
			messages.success(request, "Tutor cadastrado com sucesso")
	else:
		form = TutorForm()
	return render(request, 'web_page/tutor_form.html', locals())

@login_required
def encontro_form(request):
	if request.method == "POST":
		form = EncontroForm(request.POST)
		if form.is_valid():
			form.save()
			messages.success(request, "Encontro cadastrado com sucesso")
	else:
		form = EncontroForm()
	return render(request, 'web_page/encontro_form.html', locals())

@login_required
def inscrito_form(request):
	if request.method == "POST":
		form = InscritoForm(request.POST)
		if form.is_valid():
			form.save()
			messages.success(request, "Inscrito adicionado com sucesso")
	else:
		form = InscritoForm()
	return render(request, 'web_page/inscrito_form.html', locals())

@login_required
def feedback_form(request):
	if request.method == "POST":
		form = FeedbackForm(request.POST, request.FILES)
		if form.is_valid():
			form.save()
			messages.success(request, "Feedback adicionado com sucesso")
	else:
		form = FeedbackForm()
	return render(request, 'web_page/feedback_form.html', locals())

@login_required
def atividade_form(request):
	if request.method == "POST":
		form = AtividadeForm(request.POST)
		if form.is_valid():
			form.save()
			messages.success(request, "Atividade adicionada com sucesso")
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
	encontros = Encontro.objects.filter(evento_id=id_recebido)
	if request.method == "POST":
		for encont in request.POST.getlist("check_encontros"):
			print("A DELETAR: ", encont)
			Encontro.objects.filter(id=encont).delete()
	return render(request, "web_page/encontro_list.html", locals())

@login_required
def atividades_list(request):
	id_recebido = request.POST.get("encontro_id")
	atividades = Atividade.objects.filter(encontro_id=id_recebido)
	if request.method == "POST":
		for ativ in request.POST.getlist("check_atividades"):
			Atividade.objects.filter(id=ativ).delete()
	return render(request, "web_page/atividades_list.html", locals())

@login_required
def inscFeed_list(request):
	feed_id = request.POST.get("inscrito_id")
	insc = Inscrito.objects.filter(id=feed_id)
	insc_feedbacks1 = Feedback.objects.raw("select f.id, f.status, f.timestamp, f.dir_audio, a.descricao, i.nome as nome_i, t.nome as nome_t from feedback f inner join inscritos i on f.inscrito_id = i.id inner join tutores t on f.tutor_id = t.id inner join atividades a on f.atividade_id = a.id where i.id = " + feed_id)
	for item in insc:
		nome_insc = item
	return render(request, "web_page/inscFeed_list.html", locals())

@login_required
def ativFeed_list(request):
	ativ_id = request.POST.get("atividade_id")
	ativ = Atividade.objects.filter(id=ativ_id)
	ativ_feedbacks = Feedback.objects.raw("select f.id, f.status, f.timestamp, f.dir_audio, a.descricao, i.nome as nome_i, t.nome as nome_t from feedback f inner join inscritos i on f.inscrito_id = i.id inner join tutores t on f.tutor_id = t.id inner join atividades a on f.atividade_id = a.id where a.id = " + ativ_id)
	for item in ativ:
		nome_ativ = item
	return render(request, "web_page/ativFeed_list.html", locals())

@login_required
def statistic_enc(request):
	enc_id = request.POST.get("encontro_id")
	encontro_data = Encontro.objects.filter(id=enc_id).values("evento_id")
	qnt_ativ = Atividade.objects.filter(encontro_id=enc_id).count()
	eve_id = encontro_data[0]["evento_id"]
	qnt_insc = EventoInscrito.objects.filter(evento_id=eve_id).count()

	ativ_forEnc = Atividade.objects.filter(encontro_id=enc_id).values("id")
	count = 0
	for id in ativ_forEnc:
		count = count + Feedback.objects.filter(atividade_id=id["id"], status=1).count()
	# total de atividades no encontro
	total = qnt_insc * qnt_ativ
	# porcentagem de exercicios prontos pelo encontro
	perc = 0
	if total != 0:
		perc = int((count / total) * 100)
	return render(request, "web_page/statistic_enc.html", locals())