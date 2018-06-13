<template>
   <v-card>
    <v-container grid-list-md>
     <v-layout row >
      <v-flex xs1>
       <p><span class="dot" :style="{ 'background-color': figure.fillStyle }"></span></p>
      </v-flex>
      <v-flex xs12 text-xs-left>
         <v-container grid-list-md text-xs-center>
            <v-layout row >
              <v-flex xs4>
                 Object Id: {{figure.objectId}}
              </v-flex>
               <v-flex xs3>
                 X: {{figure.x}}
              </v-flex>
               <v-flex xs3>
                 Y: {{figure.y}}
              </v-flex>
            </v-layout>
          </v-container>
      </v-flex>
     </v-layout>
    </v-container>
   </v-card>
</template>

<script>
 export default {
   name: 'ObjectCard',
   props: {
     figure: Object
   }
 }
</script>

<style scoped>
.dot {
  height: 15px;
  width: 15px;
  background-color: #bbb;
  border-radius: 50%;
  border:1px solid #003300;
  display: inline-block;
  -moz-border-radius:50%;
  -webkit-border-radius:50%;
  vertical-align: middle;
  margin-top: 10px;
}
</style>
