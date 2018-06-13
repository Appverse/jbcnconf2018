<template>
   <v-container grid-list-md>
     <v-layout row wrap>
      <v-flex xs3>
         <img src="../assets/Duke.png" width="36" height="48" >
         <v-container grid-list-md>
            <v-layout column wrap>
              <transition-group name="list" tag="div">
                <v-flex v-for="figure of figures" :key="figure.objectId">
                  <ObjectCard :figure="figure" />
                </v-flex>
              </transition-group>
            </v-layout>
         </v-container>
      </v-flex>
      <v-flex xs6>
        <div>
         <canvas id="myCanvas" width="800" height="600" style="border:1px solid #000000;"></canvas>
        </div>
      </v-flex>
    </v-layout>
   </v-container>
</template>

<script>
import axios from 'axios'
import ObjectCard from './ObjectCard'

export default {
  name: 'Drawer',
  components: {
    ObjectCard
  },
  props: {
    msg: String
  },
  data () {
    return {
      figures: []
    }
  },
  methods: {
    drawBoard (context) {
      context.lineWidth = 0.2
      for (var a = 0; a <= 800; a += 50) {
        context.moveTo(a, 0)
        context.lineTo(a, 600)
      }
      for (var b = 0; b <= 800; b += 50) {
        context.moveTo(0, b)
        context.lineTo(800, b)
      }
      context.strokeStyle = 'black'
      context.stroke()
    },
    animate (index, prop, val, duration) {
      // The calculations required for the step function
      var start = new Date().getTime()
      var end = start + duration
      var current = this.figures[index][prop]
      var distance = val - current
      let self = this
      var step = function () {
        // Get our current progres
        var timestamp = new Date().getTime()
        var progress = Math.min((duration - (end - timestamp)) / duration, 1)
        // Update the square's property
        self.figures[index][prop] = current + (distance * progress)
       // If the animation hasn't finished, repeat the step.
        if (progress < 1) requestAnimationFrame(step)
      }
      // Start the animation
      return step()
    },
    render () {
      var canvas = document.getElementById('myCanvas')
      var context = canvas.getContext('2d')
      context.clearRect(0, 0, 800, 600)
      this.drawBoard(context)
      this.figures.forEach(figure => {
        context.beginPath()
        context.arc(figure.x, figure.y, figure.radius, 0, 2 * Math.PI, false)
        context.fillStyle = figure.fillStyle
        context.fill()
        context.lineWidth = figure.lineWidth
        context.strokeStyle = figure.stroke
        context.stroke()
      })
      // Redraw
      requestAnimationFrame(this.render)
    }
  },
  mounted: function () {
    let es = new EventSource(`${process.env.ROOT_API}/positions/stream`)
    es.addEventListener('message', event => {
      const eventPos = JSON.parse(event.data)
      console.log(eventPos)
      let figureIndex = this.figures.findIndex(figure => {
        return figure.objectId === eventPos.position.objectId
      })
      if (figureIndex >= 0) {
        if (eventPos.position.createdAt > this.figures[figureIndex].createdAt) {
          this.animate(figureIndex, 'x', eventPos.position.x, 1000)
          this.animate(figureIndex, 'y', eventPos.position.y, 1000)
        }
      } else {
        let figure = {
          objectId: eventPos.position.objectId,
          x: eventPos.position.x,
          y: eventPos.position.y,
          radius: 5,
          stroke: '#003300',
          fillStyle: 'hsl(' + 360 * Math.random() + ', 50%, 50%)',
          lineWidth: 2,
          createdAt: eventPos.position.createdAt
        }
        this.figures.push(figure)
        this.render()
      }
    }, false)
    es.addEventListener('error', event => {
      if (event.readyState === EventSource.CLOSED) {
        console.log('Event was closed')
        console.log(EventSource)
      }
    }, false)
  },
  created: function () {
    axios.get(`${process.env.ROOT_API}/positions`)
      .then(response => {
        let positions = response.data
        positions.forEach(position => {
          let figure = {
            objectId: position.objectId,
            x: position.x,
            y: position.y,
            radius: 5,
            stroke: '#003300',
            fillStyle: 'hsl(' + 360 * Math.random() + ', 50%, 50%)',
            lineWidth: 2,
            createdAt: position.createdAt
          }
          this.figures.push(figure)
        })
        this.render()
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.list-item {
  display: inline-block;
  margin-right: 10px;
}
.list-enter-active, .list-leave-active {
  transition: all 1s;
}
.list-enter, .list-leave-to /* .list-leave-active below version 2.1.8 */ {
  opacity: 0;
  transform: translateY(30px);
}
</style>
