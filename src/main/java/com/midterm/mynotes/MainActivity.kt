package com.midterm.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.midterm.mynotes.model.Notes
import com.midterm.mynotes.ui.theme.MyNotesTheme
import com.midterm.mynotes.viewmodel.NoteViewmode
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val noteViewmode = viewModel<NoteViewmode>()
            MyNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Homepage(noteViewmode)
                }
            }
        }
    }
}

//fun AddNote(notes: Notes){
//

//}

@Composable
fun Homepage(noteViewmode: NoteViewmode){
    var txtname by remember { mutableStateOf("") }
    var txttitle by remember { mutableStateOf("") }
    Scaffold(
        backgroundColor = Color.White,
        topBar = { TopAppBar(
            contentColor = Color.White,

            title = {Text("This is Note Application")},backgroundColor =Color(0xffFF4791))  },
        content = {
            Column (modifier = Modifier.padding(20.dp)){

                Box(modifier = Modifier.padding(horizontal = 8.dp)){
                    Text(text = "Title of note:",

                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        ))
                }

            TextField(
                value = txtname,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                onValueChange = {
                    txtname = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(14.dp),

                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xffFFADCE),
                focusedIndicatorColor =  Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color(0xffFFADCE))
            )


               Box(modifier = Modifier.padding(horizontal = 8.dp)){
                   Text(text = "Name of note:",

                       style = TextStyle(
                           color = Color.Black,
                           fontSize = 16.sp
                       ))
               }



            TextField(
                value = txttitle,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                onValueChange = {
                    txttitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(14.dp),

                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xffFFADCE),
                    focusedIndicatorColor =  Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color(0xffFFADCE))
            )

//                Box(modifier = Modifier
//                    .fillMaxSize()
//                    .padding(vertical = 12.dp)
//                ){

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Button(onClick = {
                        if(txtname != "" && txttitle != ""){
                            var note = Notes(title =  txttitle, Name = txtname, Date = Date())
                            noteViewmode.addnotes(note)
                        }
                    },

                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF4791)),
                    ) {
                        Text("Save note", style = TextStyle(
                            fontSize = 18.sp
                        ))
                    }

                }
                ListNote(noteViewmode = noteViewmode)
//                }
        } },
    )
}


@Composable
fun ListNote(noteViewmode: NoteViewmode){
//    val noteViewmode = viewModel<NoteViewmode>()
    val listnote by noteViewmode.notelist.collectAsState()
    Box(modifier = Modifier.height(300.dp)){
        LazyColumn (  ){
            items(listnote) { item ->
                Card(
                    shape = RoundedCornerShape(18),
                    backgroundColor = Color(0xFFFFC9DF),
                    modifier = Modifier.padding(all = 10.dp)
                ) {
                    Row(

                        modifier = Modifier
                            .padding(all = 12.dp)
                            .background(Color(0xFFFFC9DF)),

                        ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(text = item.title.toString(), style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black))
                            Text(text = item.Name.toString(), style = TextStyle(fontSize = 18.sp, color = Color.Black))
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNotesTheme {
        Greeting("Android")
    }
}