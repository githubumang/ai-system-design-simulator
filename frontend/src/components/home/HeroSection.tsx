import { Link } from "react-router-dom"
import { ArrowRight, Sparkles } from "lucide-react"

export function HeroSection() {
  return (
    <section id="home" className="relative overflow-hidden">
      {/* gradient backdrop */}
      <div
        aria-hidden="true"
        className="pointer-events-none absolute inset-0 -z-10 bg-gradient-to-b from-accent/40 via-background to-background"
      />
      <div
        aria-hidden="true"
        className="pointer-events-none absolute -top-40 left-1/2 -z-10 h-[36rem] w-[36rem] -translate-x-1/2 rounded-full bg-primary/20 blur-[120px]"
      />

      <div className="mx-auto grid max-w-[1280px] grid-cols-1 items-center gap-12 px-4 py-20 sm:px-6 lg:grid-cols-2 lg:gap-8 lg:py-28 lg:px-8">
        <div className="flex flex-col items-start text-left">
          <span className="inline-flex items-center gap-2 rounded-full border border-border bg-card/60 px-3.5 py-1.5 text-xs font-medium text-muted-foreground backdrop-blur">
            <Sparkles className="h-3.5 w-3.5 text-primary" aria-hidden="true" />
            AI-powered interview prep
          </span>

          <h1 className="mt-6 text-balance text-4xl font-semibold tracking-tight sm:text-5xl lg:text-6xl">
            Master System Design Interviews with AI
          </h1>

          <p className="mt-6 max-w-xl text-pretty text-base leading-relaxed text-muted-foreground sm:text-lg">
            Practice real-world system design questions, submit your solutions, and receive detailed AI-powered feedback
            to improve your interview performance.
          </p>

          <div className="mt-8 flex flex-col gap-3 sm:flex-row">
            <Link
              to="/questions"
              className="group inline-flex items-center justify-center gap-1.5 rounded-full bg-primary px-6 py-3 text-sm font-medium text-primary-foreground shadow-sm transition-all hover:opacity-90 hover:shadow-md"
            >
              Start Practicing
              <ArrowRight className="h-4 w-4 transition-transform group-hover:translate-x-0.5" aria-hidden="true" />
            </Link>
            <Link
              to="/questions"
              className="inline-flex items-center justify-center rounded-full border border-border bg-card px-6 py-3 text-sm font-medium text-foreground transition-colors hover:bg-muted"
            >
              Browse Questions
            </Link>
          </div>

          <dl className="mt-12 grid grid-cols-3 gap-6 border-t border-border/60 pt-8">
            {[
              { n: "200+", l: "Questions" },
              { n: "10k+", l: "Submissions" },
              { n: "4.9/5", l: "Avg rating" },
            ].map((s) => (
              <div key={s.l}>
                <dt className="text-2xl font-semibold tracking-tight">{s.n}</dt>
                <dd className="mt-1 text-xs text-muted-foreground">{s.l}</dd>
              </div>
            ))}
          </dl>
        </div>

        <div className="relative">
          <div className="absolute inset-0 -z-10 rounded-3xl bg-primary/20 blur-2xl" aria-hidden="true" />
          <div className="overflow-hidden rounded-3xl border border-border bg-card shadow-2xl">
            <img
              src="/hero-system-design.png"
              alt="Abstract visualization of a system design architecture with interconnected servers and databases"
              width={1024}
              height={1024}
              className="h-full w-full object-cover"
            />
          </div>
        </div>
      </div>
    </section>
  )
}
