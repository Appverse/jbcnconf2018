<template>
  <v-app>
    <v-toolbar color="primary" app>
    </v-toolbar>
    <v-content>
       <v-container grid-list-md text-xs-center>
    <v-layout row wrap>
      <v-flex xs12>
           <img src="./assets/logo.png" width="36" height="48" >
      </v-flex>
       <v-flex xs12>
         <Drawer/>
      </v-flex>
    </v-layout>
    </v-container>
    </v-content>
    <v-footer :fixed="fixed" app>
      <span>&copy; 2018</span>
    </v-footer>
  </v-app>
</template>

<script>
import Drawer from './components/Drawer'

export default {
  data () {
    return {
      fixed: false
    }
  },
  name: 'App',
  components: {
    Drawer
  }
}
</script>
