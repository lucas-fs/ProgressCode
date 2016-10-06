from django import forms
from django.contrib import admin
from django.conf import settings
from web_service.models import *

class EventoForm(forms.ModelForm):
	class Meta:
		model = Evento
		fields = ['nome_evento', 'descricao']

class EquipeForm(forms.ModelForm):
	class Meta:
		model = Equipe
		fields = ['descricao']

class TutorForm(forms.ModelForm):
	class Meta:
		model = Tutor
		fields = ['nome', 'email', 'senha']

class InscritoForm(forms.ModelForm):
	class Meta:
		model = Inscrito
		fields = ['nome', 'data_nasc', 'escola']
		widgets = {
			'data_nasc' : forms.DateInput(format=settings.DATE_INPUT_FORMATS)
		}

class EncontroForm(forms.ModelForm):
	class Meta:
		model = Encontro
		fields = ['data_realizao', 'evento']
		widgets = {
			'data_realizao' : forms.SelectDateWidget(empty_label="Nothing")
		}

class FeedbackForm(forms.ModelForm):
	class Meta:
		model = Feedback
		fields = '__all__'

class AtividadeForm(forms.ModelForm):
	class Meta:
		model = Atividade
		fields = ['descricao', 'encontro']
