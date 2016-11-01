from django.db import models

class Tutor(models.Model):
    nome = models.CharField(max_length=100)
    email = models.EmailField(max_length=254)
    senha = models.CharField(max_length=30)

    def __str__(self):
        return self.nome

    class Meta:
        db_table  =  'tutores'
        verbose_name = "tutor"
        verbose_name_plural = "tutores"

class Equipe(models.Model):
    descricao = models.CharField(max_length=100)
    membros = models.ManyToManyField(Tutor, through='EquipeTutor')

    def __str__(self):
        return self.descricao

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

class Inscrito(models.Model):
    nome = models.CharField(max_length=100)
    data_nasc = models.DateField(auto_now=False)
    escola = models.CharField(max_length=100)

    def __str__(self):
        return self.nome

    class Meta:
        db_table  =  'inscritos'
        verbose_name = "inscrito"
        verbose_name_plural = "inscritos"

class Evento(models.Model):
    nome_evento = models.CharField(max_length=150)
    descricao = models.CharField(max_length=200)
    equipes = models.ManyToManyField(Equipe, through='EventoEquipe')
    inscritos = models.ManyToManyField(Inscrito, through='EventoInscrito')

    def __str__(self):
        return self.nome_evento

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

class EventoInscrito(models.Model):
    evento =  models.ForeignKey(Evento, on_delete=models.CASCADE)
    inscrito =  models.ForeignKey(Inscrito, on_delete=models.CASCADE)

    class Meta:
        db_table  =  'evento_inscrito'
        verbose_name = "evento_inscrito"
        verbose_name_plural = "evento_inscrito"

class Encontro(models.Model):
    data_realizao = models.DateField(auto_now=False)
    evento = models.ForeignKey(Evento, on_delete=models.CASCADE)

    def __str__(self):
        return str(self.data_realizao)

    class Meta:
        db_table  =  'encontros'
        verbose_name = "encontro"
        verbose_name_plural = "encontros"

class Atividade(models.Model):
    descricao = models.CharField(max_length=200)
    encontro = models.ForeignKey(Encontro, on_delete=models.CASCADE)

    def __str__(self):
        return self.descricao

    class Meta:
        db_table  =  'atividades'
        verbose_name = "atividade"
        verbose_name_plural = "atividades"

class Feedback(models.Model):
    status = models.IntegerField()
    timestamp = models. DateTimeField(auto_now=True)
    dir_audio = models.FileField(upload_to='sounds/')
    tutor = models.ForeignKey(Tutor, on_delete=models.CASCADE)
    inscrito = models.ForeignKey(Inscrito, on_delete=models.CASCADE)
    atividade = models.ForeignKey(Atividade, on_delete=models.CASCADE)
    observacao = models.TextField(max_length=500)

    class Meta:
        db_table  =  'feedback'
        verbose_name = "feedback"
        verbose_name_plural = "feedbacks"