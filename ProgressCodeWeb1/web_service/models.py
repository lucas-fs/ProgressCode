from __future__ import unicode_literals

from django.db import models

class Tutor(models.Model):
    nome = models.CharField(max_length=100)
    email = models.EmailField(max_length=254)
    senha = models.CharField(max_length=30)

    class Meta:
        db_table  =  'tutores'
        verbose_name = "tutor"
        verbose_name_plural = "tutores"

class Equipe(models.Model):
    descricao = models.CharField(max_length=100)
    membros = models.ManyToManyField(Tutor, through='EquipeTutor')

    class Meta:
        db_table  =  'equipes'
        verbose_name = "equipe"
        verbose_name_plural = "equipes"

class EquipeTutor(models.Model):
    tutor = models.ForeignKey(Tutor, on_delete=models.CASCADE)
    equipe = models.ForeignKey(Equipe, on_delete=models.CASCADE)

    class Meta:
        db_table  =  'equipe_tutor'
        verbose_name = "equipe_tutor"
        verbose_name_plural = "equipe_tutor"

class Evento(models.Model):
    nome_evento = models.CharField(max_length=150)
    descricao = models.CharField(max_length=200)
    equipes = models.ManyToManyField(Equipe, through='EventoEquipe')
    inscritos = models.ManyToManyField(Inscrito, through='EventoInscrito')

    class Meta:
        db_table  =  'eventos'
        verbose_name = "evento"
        verbose_name_plural = "eventos"

class EventoEquipe(models.Model):
    evento =  models.ForeignKey(Evento, on_delete=models.CASCADE)
    equipe =  models.ForeignKey(Equipe, on_delete=models.CASCADE)

    class Meta:
        db_table  =  'evento_equipe'
        verbose_name = "evento_equipe"
        verbose_name_plural = "evento_equipe"

class Encontro(models.Model):
    data_realizao = models.DateField(auto_now=False, auto_now_add=False)
    evento = models.ForeignKey(Evento, on_delete=models.CASCADE)

    class Meta:
        db_table  =  'encontros'
        verbose_name = "encontro"
        verbose_name_plural = "encontros"

class Atividade(models.Model):
    descricao = models.CharField(max_length=200)
    encontro = models.ForeignKey(Encontro, on_delete=models.CASCADE)

    class Meta:
        db_table  =  'atividades'
        verbose_name = "atividade"
        verbose_name_plural = "atividades"

class Inscrito(models.Model):
    nome = models.CharField(max_length=100)
    data_nasc = models.DateField(auto_now=False, auto_now_add=False)
    escola = models.CharField(max_length=100)

    class Meta:
        db_table  =  'inscritos'
        verbose_name = "inscrito"
        verbose_name_plural = "inscritos"

class EventoInscrito(models.Model):
    evento =  models.ForeignKey(Evento, on_delete=models.CASCADE)
    inscrito =  models.ForeignKey(Inscrito, on_delete=models.CASCADE)

    class Meta:
        db_table  =  'evento_inscrito'
        verbose_name = "evento_inscrito"
        verbose_name_plural = "evento_inscrito"

class Feedback(models.Model):
    status = models.IntegerField()
    timestamp = models. DateTimeField(auto_now=True, auto_now_add=True)
    dir_audio = models.FileField(upload_to='uploads/%Y/%m/%d/')
    tutor = models.ForeignKey(Tutor, on_delete=models.PROTECT)
    inscrito = models.ForeignKey(Inscrito, on_delete=models.PROTECT)
    atividade = models.ForeignKey(Atividade, on_delete=models.PROTECT)

    class Meta:
        db_table  =  'feedback'
        verbose_name = "feedback"
        verbose_name_plural = "feedbacks"
