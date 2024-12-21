package com.example.a18_1_column_row_box_surface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val persons = generatePersonList()
        setContent {
            PersonList(persons)
        }
    }

    private fun generatePersonList(): List<Person> {
        val persons = mutableListOf<Person>()
        repeat(10) {
            persons.add(Person(getRandomName(), getRandomSalary(), getRandomPhone()))
        }
        return persons
    }

    private fun getRandomName(): String {
        val names = listOf<String>(
            "Иван",
            "Анна",
            "Владимир",
            "Александра",
            "Игорь",
            "Виктория",
            "Дмитрий",
            "Ирина",
            "Николай"
        )
        return names[Random.nextInt(names.size)]
    }

    private fun getRandomSalary(): Int = Random.nextInt(250_000) + 50_000

    private fun getRandomPhone(): String {
        var phone = "+79"
        repeat(9) {
            phone += Random.nextInt(10)
        }
        return phone
    }
}

val titleModifier = Modifier
    .fillMaxWidth()
    .background(Color.DarkGray)
    .border(width = 2.dp, color = Color.Black)
    .padding(horizontal = 20.dp, vertical = 10.dp)

val rowModifier = Modifier
    .fillMaxWidth()
    .border(width = 2.dp, color = Color.Gray)
    .padding(horizontal = 20.dp, vertical = 10.dp)

@Composable
fun PersonList(persons: List<Person>) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                Column(Modifier.padding(all = 16.dp)) {
                    Row(
                        modifier = titleModifier,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(text = "Данные по персоналу:", fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier =
                        Modifier
                            .background(Color.LightGray)
                            .then(rowModifier),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Имя", fontWeight = FontWeight.Bold)
                        Text(text = "Зарплата", fontWeight = FontWeight.Bold)
                        Text(text = "Телефон", fontWeight = FontWeight.Bold)
                    }
                    persons.forEach { person ->
                        PersonItem(person)
                    }
                }
            }
        }
    )

}

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = person.name)
        Text(text = "${person.salary} руб.")
        Text(text = person.phone)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPersonList() {
    val previewPersons = listOf(
        Person("Иван", 100_000, "+79001234567"),
        Person("Анна", 200_000, "+79009876543")
    )
    PersonList(previewPersons)
}