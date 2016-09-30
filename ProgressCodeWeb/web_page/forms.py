from django import forms
from django.contrib import admin
from web_service.models import *

class TutorForm(forms.ModelForm):
	class Meta:
		model = Tutor
		fields = ['nome', 'email', 'senha']

class EquipeForm(forms.ModelForm):
	class Meta:
		model = Equipe
		fields = ['descricao', 'membros']

class EventoForm(forms.ModelForm):
	membros = forms.ModelMultipleChoiceField(queryset=Tutor.objects.all())

	class Meta:
		model = Evento
		fields = ['nome_evento']

class EncontroForm(forms.ModelForm):
	evento = forms.ModelMultipleChoiceField(queryset=Evento.objects.all())

	class Meta:
		model = Encontro
		fields = ['data_realizao']
