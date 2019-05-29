package com.example.drcreeper.refereeapp;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {


    @BindingAdapter({"app:manager"})
    public static void setLayoutManager(RecyclerView recyclerView, int cols){
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),cols,RecyclerView.VERTICAL,false));
        //new GridLayoutManager(recyclerView.getContext(),cols);
    }

    @BindingAdapter({"app:visible"})
    public static void setVisiblity(View v, boolean visible){
        if(visible) {
            v.setVisibility(View.VISIBLE);
        }else {
            v.setVisibility(View.INVISIBLE);
        }
    }
    /*
    @BindingAdapter({"app:res_id"})
    public static void setFileIcon(ImageView imageView, int resId){
        imageView.setImageResource(resId);
    }

    <?php
include("../php/usercheck.php");
$json;
$access = get_access($_POST['id'],$_POST['pass'],$_POST['contest']);
if($access>=0){
    $sql = "SELECT * FROM `competitions` WHERE `id` = ".$_POST['contest'].";";
    $contest = mquery($sql);
    if($contest['group'] == 0){
        $sql = "SELECT * FROM `headers` WHERE `competition_id` = ".$contest['id'].";";
        $result = mysqli_query($connection,$sql);
        $headers = array();
        while($header = mysqli_fetch_assoc($result)){
            $headers[] = $header;
        }
        $sql = "SELECT * FROM `fields` WHERE `competition_id` = ".$contest['id'].";";
        $result = mysqli_query($connection,$sql);
        $fields = array();
        while($field = mysqli_fetch_assoc($result)){
            $fields[] = $field;
        }
        $sql = "SELECT * FROM `info` WHERE `competition_id` = ".$contest['id']." ORDER BY `team_id`;";
        $result = mysqli_query($connection,$sql);
        $teams = array();
        while($team = mysqli_fetch_assoc($result)){
            $teams[] = $team;
        }

        $json["headers"] = $headers;
        $json["fields"] = $fields;
        $json["teams"] = $teams;
    }else{
        $json = ["result"=> -1];
    }
}else{
    $json = ["result"=> -2];
}
echo(json_encode($json,JSON_NUMERIC_CHECK));
?>
    */
}
