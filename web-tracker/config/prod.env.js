'use strict'
module.exports = {
  NODE_ENV: '"production"',
  ROOT_API: process.env.ROOT_API || '"http://localhost:32523"'  //kubernetes Traefik exposed port
}
