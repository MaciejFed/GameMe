#!/usr/bin/env python
import web
urls = (
    '/restart', 'restart_server'
)
class MyApplication(web.application):
    def run(self, port=8999, *middleware):
        func = self.wsgifunc(*middleware)
        return web.httpserver.runsimple(func, ('0.0.0.0', port))
class restart_server:        
    def POST(self):
        import subprocess
        print "Restarting service..."
        subprocess.call("./runApp.sh", shell=True)
        print "end"
app = MyApplication(urls, globals())
if __name__ == "__main__":
    app.run()
