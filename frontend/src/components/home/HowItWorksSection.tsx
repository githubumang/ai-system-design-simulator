import { MousePointerClick, Send, MessageSquareCheck } from "lucide-react"

const steps = [
  {
    icon: MousePointerClick,
    step: "Step 1",
    title: "Choose a Question",
    description: "Pick from a curated library of real-world system design and backend engineering challenges.",
  },
  {
    icon: Send,
    step: "Step 2",
    title: "Submit Your Solution",
    description: "Outline your architecture, APIs, data models, and scalability trade-offs in your own words.",
  },
  {
    icon: MessageSquareCheck,
    step: "Step 3",
    title: "Receive AI Evaluation",
    description: "Get instant, structured feedback with scores, strengths, and actionable improvements.",
  },
]

export function HowItWorksSection() {
  return (
    <section id="how-it-works" className="border-y border-border/60 bg-muted/30">
      <div className="mx-auto max-w-[1280px] px-4 py-20 sm:px-6 lg:py-28 lg:px-8">
        <div className="mx-auto max-w-2xl text-center">
          <h2 className="text-balance text-3xl font-semibold tracking-tight sm:text-4xl">How It Works</h2>
          <p className="mt-4 text-pretty text-base leading-relaxed text-muted-foreground">
            Three simple steps to start improving your interview performance today.
          </p>
        </div>

        <div className="relative mt-16 grid grid-cols-1 gap-10 md:grid-cols-3">
          {/* connecting line */}
          <div
            aria-hidden="true"
            className="absolute left-0 right-0 top-7 hidden h-px bg-gradient-to-r from-transparent via-border to-transparent md:block"
          />
          {steps.map((step) => (
            <div key={step.step} className="relative flex flex-col items-center text-center">
              <span className="relative z-10 flex h-14 w-14 items-center justify-center rounded-2xl border border-border bg-card text-primary shadow-sm">
                <step.icon className="h-6 w-6" aria-hidden="true" />
              </span>
              <span className="mt-5 text-xs font-medium uppercase tracking-wider text-primary">{step.step}</span>
              <h3 className="mt-2 text-lg font-semibold tracking-tight">{step.title}</h3>
              <p className="mt-2 max-w-xs text-sm leading-relaxed text-muted-foreground">{step.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}
