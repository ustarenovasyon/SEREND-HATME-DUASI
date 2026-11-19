package com.serhendi.hatme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.launch

private val ComponentActivity.dataStore by preferencesDataStore(name = "hatme_progress")
private val emerald = Color(0xFF123D32)
private val cream = Color(0xFFF7F2E6)
private val gold = Color(0xFFC5A45D)
private val ink = Color(0xFF20352F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val prefs by dataStore.data.collectAsState(initial = androidx.datastore.preferences.core.emptyPreferences())
            val completed = remember(prefs) { (1..30).filter { prefs[booleanPreferencesKey("done_$it")] == true }.toSet() }
            var screen by remember { mutableStateOf("home") }
            var lessonIndex by remember { mutableIntStateOf(0) }
            var partIndex by remember { mutableIntStateOf(0) }
            var hidden by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()
            val flatParts = remember { lessons.flatMapIndexed { li, lesson -> lesson.parts.map { li to it } } }
            val total = 30
            val percent = completed.size * 100 / total
            val lastLesson = prefs[intPreferencesKey("last_lesson")] ?: 0
            val lastPart = prefs[intPreferencesKey("last_part")] ?: 0
            MaterialTheme {
                Surface(color = cream, modifier = Modifier.fillMaxSize()) {
                    when(screen) {
                        "lessons" -> LessonsScreen(completed, onBack={screen="home"}, onOpen={li,pi->
                            lessonIndex=li; partIndex=pi; hidden=false; screen="study"
                            scope.launch { dataStore.edit { it[intPreferencesKey("last_lesson")]=li; it[intPreferencesKey("last_part")]=pi } }
                        })
                        "progress" -> ProgressScreen(completed, onBack={screen="home"})
                        "study" -> {
                            val lesson = lessons[lessonIndex]
                            val part = lesson.parts[partIndex]
                            StudyScreen(lesson.title,part,completed.contains(part.number),hidden,
                                onBack={screen="lessons"},onToggleHide={hidden=!hidden},
                                onMark={done->scope.launch{dataStore.edit{it[booleanPreferencesKey("done_${part.number}")]=done}} },
                                onPrev={ if(partIndex>0) partIndex-- else if(lessonIndex>0){lessonIndex--;partIndex=lessons[lessonIndex].parts.lastIndex}; hidden=false
                                    scope.launch{dataStore.edit{it[intPreferencesKey("last_lesson")]=lessonIndex;it[intPreferencesKey("last_part")]=partIndex}} },
                                onNext={ if(partIndex<lesson.parts.lastIndex) partIndex++ else if(lessonIndex<lessons.lastIndex){lessonIndex++;partIndex=0}; hidden=false
                                    scope.launch{dataStore.edit{it[intPreferencesKey("last_lesson")]=lessonIndex;it[intPreferencesKey("last_part")]=partIndex}} })
                        }
                        else -> HomeScreen(percent,completed.size,onLessons={screen="lessons"},onContinue={
                            lessonIndex=lastLesson.coerceIn(0,lessons.lastIndex)
                            partIndex=lastPart.coerceIn(0,lessons[lessonIndex].parts.lastIndex)
                            screen="study";hidden=false
                        },onProgress={screen="progress"})
                    }
                }
            }
        }
    }
}

@Composable
fun AppTitle() {
    Column(horizontalAlignment=Alignment.CenterHorizontally, modifier=Modifier.fillMaxWidth()) {
        Text("✦  SERHENDÎ  ✦",color=emerald,fontSize=13.sp,fontWeight=FontWeight.Bold,letterSpacing=3.sp)
        Text("Hatme Duası",color=ink,fontSize=28.sp,fontWeight=FontWeight.Bold)
        Text("EZBER VE İLERLEME",color=gold,fontSize=11.sp,letterSpacing=2.sp)
    }
}

@Composable
fun HomeScreen(percent:Int,done:Int,onLessons:()->Unit,onContinue:()->Unit,onProgress:()->Unit) {
    Column(Modifier.fillMaxSize().padding(22.dp),horizontalAlignment=Alignment.Center,verticalArrangement=Arrangement.spacedBy(18.dp)) {
        Spacer(Modifier.height(18.dp)); AppTitle()
        Card(colors=CardDefaults.cardColors(containerColor=emerald),shape=RoundedCornerShape(24.dp),modifier=Modifier.fillMaxWidth()) {
            Column(Modifier.padding(24.dp),horizontalAlignment=Alignment.Center,verticalArrangement=Arrangement.spacedBy(8.dp)) {
                Text("EZBER İLERLEMEN",color=cream,letterSpacing=2.sp)
                Text("$percent%",fontSize=54.sp,color=gold,fontWeight=FontWeight.Bold)
                LinearProgressIndicator(progress={percent/100f},color=gold,trackColor=Color(0xFF426B5E),modifier=Modifier.fillMaxWidth().height(8.dp))
                Text("$done / 30 parça tamamlandı",color=cream)
            }
        }
        Button(onClick=onContinue,colors=ButtonDefaults.buttonColors(containerColor=gold,contentColor=emerald),modifier=Modifier.fillMaxWidth().height(58.dp)) { Text("Kaldığım yerden devam et",fontSize=17.sp,fontWeight=FontWeight.Bold) }
        OutlinedButton(onClick=onLessons,modifier=Modifier.fillMaxWidth().height(56.dp)) { Text("Derslere göz at",fontSize=17.sp,color=emerald) }
        OutlinedButton(onClick=onProgress,modifier=Modifier.fillMaxWidth().height(56.dp)) { Text("Ezber ilerlemem",fontSize=17.sp,color=emerald) }
        Spacer(Modifier.weight(1f))
        Text("Metin cihazınızda saklanır • Çevrimdışı kullanım",color=ink.copy(alpha=.65f),fontSize=12.sp,textAlign=TextAlign.Center)
    }
}

@Composable
fun LessonsScreen(completed:Set<Int>,onBack:()->Unit,onOpen:(Int,Int)->Unit) {
    Column(Modifier.fillMaxSize().padding(horizontal=16.dp,vertical=12.dp)) {
        Row(verticalAlignment=Alignment.CenterVertically) {
            Text("‹",fontSize=32.sp,color=emerald,modifier=Modifier.clickable(onClick=onBack).padding(horizontal=12.dp))
            Text("Dersler",fontSize=25.sp,color=emerald,fontWeight=FontWeight.Bold)
        }
        Text("Dilediğin dersi istediğin zaman açabilirsin.",color=ink.copy(alpha=.7f),modifier=Modifier.padding(start=8.dp,bottom=10.dp))
        LazyColumn(verticalArrangement=Arrangement.spacedBy(10.dp)) {
            itemsIndexed(lessons) { li, lesson ->
                val count=lesson.parts.count{completed.contains(it.number)}
                val status=when { count==lesson.parts.size -> "Tamamlandı"; count>0 -> "Devam ediyor"; else -> "Başlanmadı" }
                Card(colors=CardDefaults.cardColors(containerColor=Color.White),shape=RoundedCornerShape(16.dp),modifier=Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp)) {
                        Text("${li+1}. Ders",color=gold,fontWeight=FontWeight.Bold,fontSize=12.sp)
                        Text(lesson.title,color=emerald,fontWeight=FontWeight.Bold,fontSize=17.sp)
                        Text("$count / ${lesson.parts.size} parça • $status",color=ink.copy(alpha=.75f),fontSize=13.sp)
                        LinearProgressIndicator(progress={count.toFloat()/lesson.parts.size},modifier=Modifier.fillMaxWidth().padding(top=8.dp),color=emerald,trackColor=cream)
                        lesson.parts.forEachIndexed { pi,part ->
                            Row(Modifier.fillMaxWidth().clickable{onOpen(li,pi)}.padding(vertical=8.dp),verticalAlignment=Alignment.CenterVertically) {
                                Text(if(completed.contains(part.number)) "✓" else "○",color=if(completed.contains(part.number)) emerald else gold,fontSize=18.sp)
                                Text("  Parça ${part.number}",color=ink,modifier=Modifier.weight(1f))
                                Text("Aç ›",color=emerald,fontSize=13.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudyScreen(title:String,part:HatmePart,isDone:Boolean,hidden:Boolean,onBack:()->Unit,onToggleHide:()->Unit,onMark:(Boolean)->Unit,onPrev:()->Unit,onNext:()->Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp),verticalArrangement=Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment=Alignment.CenterVertically) {
            Text("‹",fontSize=32.sp,color=emerald,modifier=Modifier.clickable(onClick=onBack).padding(horizontal=12.dp))
            Text(title,fontSize=18.sp,color=emerald,fontWeight=FontWeight.Bold)
        }
        Card(colors=CardDefaults.cardColors(containerColor=emerald),shape=RoundedCornerShape(24.dp),modifier=Modifier.fillMaxWidth().weight(1f)) {
            Column(Modifier.fillMaxWidth().padding(20.dp),horizontalAlignment=Alignment.Center,verticalArrangement=Arrangement.spacedBy(16.dp)) {
                Text("EZBER PARÇASI ${part.number}",color=gold,letterSpacing=2.sp,fontWeight=FontWeight.Bold)
                Text(if(hidden) "••••••••••••••••••" else part.text,color=cream,fontSize=23.sp,lineHeight=37.sp,textAlign=TextAlign.Center,modifier=Modifier.weight(1f).fillMaxWidth().wrapContentHeight(Alignment.Center))
                OutlinedButton(onClick=onToggleHide,colors=ButtonDefaults.outlinedButtonColors(contentColor=gold)) { Text(if(hidden) "Metni göster" else "Metni gizle") }
            }
        }
        Button(onClick={onMark(true)},colors=ButtonDefaults.buttonColors(containerColor=gold,contentColor=emerald),modifier=Modifier.fillMaxWidth().height(54.dp)) { Text(if(isDone) "✓ Ezberlendi" else "Ezberledim",fontWeight=FontWeight.Bold,fontSize=16.sp) }
        OutlinedButton(onClick={onMark(false)},modifier=Modifier.fillMaxWidth()) { Text("Henüz ezberlemedim",color=emerald) }
        Row(horizontalArrangement=Arrangement.spacedBy(8.dp),modifier=Modifier.fillMaxWidth()) {
            OutlinedButton(onClick=onPrev,modifier=Modifier.weight(1f)) { Text("← Önceki",color=emerald) }
            Button(onClick=onNext,colors=ButtonDefaults.buttonColors(containerColor=emerald),modifier=Modifier.weight(1f)) { Text("Sonraki →",color=cream) }
        }
    }
}


@Composable
fun ProgressScreen(completed:Set<Int>,onBack:()->Unit) {
    val totalDone=completed.size
    Column(Modifier.fillMaxSize().padding(16.dp),verticalArrangement=Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment=Alignment.CenterVertically) {
            Text("‹",fontSize=32.sp,color=emerald,modifier=Modifier.clickable(onClick=onBack).padding(horizontal=12.dp))
            Text("Ezber ilerlemem",fontSize=24.sp,color=emerald,fontWeight=FontWeight.Bold)
        }
        Card(colors=CardDefaults.cardColors(containerColor=emerald),shape=RoundedCornerShape(20.dp),modifier=Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp),horizontalAlignment=Alignment.Center,verticalArrangement=Arrangement.spacedBy(8.dp)) {
                Text("GENEL TAMAMLANMA",color=cream,letterSpacing=1.5.sp)
                Text("${totalDone * 100 / 30}%",fontSize=48.sp,color=gold,fontWeight=FontWeight.Bold)
                Text("$totalDone / 30 ezber parçası",color=cream)
                LinearProgressIndicator(progress={totalDone/30f},color=gold,trackColor=Color(0xFF426B5E),modifier=Modifier.fillMaxWidth().height(8.dp))
            }
        }
        LazyColumn(verticalArrangement=Arrangement.spacedBy(8.dp)) {
            itemsIndexed(lessons) { index, lesson ->
                val count=lesson.parts.count { completed.contains(it.number) }
                Card(colors=CardDefaults.cardColors(containerColor=Color.White),modifier=Modifier.fillMaxWidth(),shape=RoundedCornerShape(14.dp)) {
                    Column(Modifier.padding(14.dp)) {
                        Text("${index+1}. ${lesson.title}",fontWeight=FontWeight.Bold,color=emerald)
                        Text("$count / ${lesson.parts.size} tamamlandı",color=ink.copy(alpha=.75f),fontSize=13.sp)
                        LinearProgressIndicator(progress={count.toFloat()/lesson.parts.size},color=emerald,trackColor=cream,modifier=Modifier.fillMaxWidth().padding(top=7.dp))
                    }
                }
            }
        }
    }
}
